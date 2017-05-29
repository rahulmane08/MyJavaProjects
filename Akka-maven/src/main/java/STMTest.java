import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;
import akka.pattern.Patterns;
import akka.transactor.*;
import akka.util.Timeout;

class Account {
	public static enum TransactionTypes {
		CREDIT, DEBIT;
	}

	private Ref.View<Integer> balance;
	private String accountNumber;

	public Account(Integer balance, String accountNumber) {
		super();
		this.balance = STM.newRef(balance);
		this.accountNumber = accountNumber;
	}

	public void credit(int amount) throws Exception {
		if (amount < 0)
			throw new IllegalArgumentException();
		STM.increment(this.balance, amount);
	}

	public void debit(int amount) throws Exception {
		if (amount < 0)
			throw new IllegalArgumentException();
		if (amount <= this.balance.get())
			STM.increment(this.balance, -amount);
		else
			throw new java.lang.Exception("insufficient funds");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [balance=" + balance.get() + ", accountNumber="
				+ accountNumber + "]";
	}

}

class Message {
	private int amount;
	private Account fromAccount, toAccount;

	public Message(int amount, Account fromAccount, Account toAccount) {
		super();
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the fromAccount
	 */
	public Account getFromAccount() {
		return fromAccount;
	}

	/**
	 * @param fromAccount
	 *            the fromAccount to set
	 */
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	/**
	 * @return the toAccount
	 */
	public Account getToAccount() {
		return toAccount;
	}

	/**
	 * @param toAccount
	 *            the toAccount to set
	 */
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

}

class Debitor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Coordinated) {
			Coordinated c = (Coordinated) msg;
			final Message m = (Message) c.getMessage();
			c.atomic(new Callable() {
				public Object call() throws Exception {
					Account fromAccount = m.getFromAccount();
					int debitAmount = m.getAmount();
					System.out.println("debiting " + debitAmount + " from account "
							+ fromAccount);
					fromAccount.debit(debitAmount);
					System.out.println("after debit " + fromAccount);
					getSender().tell("done", getSelf());
					return null;
				}
			});
			
		} else
			unhandled(msg);
	}

}

class Creditor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Coordinated) {
			Coordinated c = (Coordinated) msg;
			final Message m = (Message) c.getMessage();
			c.atomic(new Callable() {
				public Object call() throws Exception {

					Account toAccount = m.getToAccount();
					int creditAmount = m.getAmount();
					System.out.println("crediting " + creditAmount
							+ " to account " + toAccount);
					toAccount.credit(creditAmount);
					System.out.println("after credit " + toAccount);
					getSender().tell("done", getSelf());
					return null;
				}
			});

		} else
			unhandled(msg);
	}

}

class MoneyTransactor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Message) {
			final ActorRef creditor = getContext().actorOf(
					Props.create(Creditor.class), "creditor");
			final ActorRef debitor = getContext().actorOf(
					Props.create(Debitor.class), "debitor");

			final Message m = (Message) msg;
			// create a coordinated transaction
			@SuppressWarnings("deprecation")
			final Coordinated coordinators = new Coordinated(new Timeout(5,
					TimeUnit.MINUTES));

			coordinators.atomic(new Runnable() {

				public void run() {					
					creditor.tell(coordinators.coordinate(m), getSelf());
					debitor.tell(coordinators.coordinate(m), getSelf());
				}
			});

		} else
			unhandled(msg);

	}

}

public class STMTest {
	public static void main(String[] args) throws Exception {
		Timeout t = new Timeout(5, TimeUnit.MINUTES);
		Account a = new Account(100, "a");
		Account b = new Account(100, "b");
		System.out.println(a + "," + b);
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef transactor = system.actorOf(
				Props.create(MoneyTransactor.class), "transactor");
		transactor.tell(new Message(100, a, b), ActorRef.noSender());
		Thread.sleep(10000);
		System.out.println(a + "," + b);
	}
}
