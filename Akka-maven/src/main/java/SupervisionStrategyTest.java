import javax.crypto.NullCipher;

import scala.concurrent.duration.Duration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;


class Child1 extends UntypedActor
{

	@Override
	public void preStart() throws Exception {
		System.out.println("starting child1");
		super.preStart();
	}
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
class Child2 extends UntypedActor
{

	@Override
	public void preStart() throws Exception {
		System.out.println("starting child2");
		super.preStart();
	}
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
class Child3 extends UntypedActor
{

	@Override
	public void preStart() throws Exception {
		System.out.println("starting child3");
		super.preStart();
	}
	@Override
	public void onReceive(Object arg0) throws Exception {
		throw new NullPointerException();
		
	}
	
}
class Supervisor extends UntypedActor
{

	private static SupervisorStrategy oneForOneStrategy = new OneForOneStrategy(10, 
															Duration.create("1 minute"), new Function<Throwable, SupervisorStrategy.Directive>() {
		
		public Directive apply(Throwable t) throws Exception {
			if(t instanceof NullPointerException)
				return ;
			return null;
		}
	});
	@Override
	public void preStart() throws Exception {
		System.out.println("starting supervisor");
		final ActorRef child1 = getContext().actorOf(Props.create(Child1.class),"child1");
		final ActorRef child2 = getContext().actorOf(Props.create(Child1.class),"child1");
		final ActorRef child3 = getContext().actorOf(Props.create(Child1.class),"child1");
		
		for(int i=0;i<10;i++)
		{
			Thread.sleep(3000);
			child3.tell(new Object(), getSelf());
		}
		
		
		super.preStart();
	}
	@Override
	public void onReceive(Object arg0) throws Exception {
		
		
	}
	
}
public class SupervisionStrategyTest 
{
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		system.actorOf(Props.create(Supervisor.class), "supervisor");
	}

}
