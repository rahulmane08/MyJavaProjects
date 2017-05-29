import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;


class SampleActor extends UntypedActor
{

	@Override
	public void onReceive(Object message) throws Exception {
		String msg = (String)message;
		if("exit".equals(msg))
			getContext().stop(getSelf());
		else if("hello".equals(msg))
			System.out.println("Hi sender");
		else
			unhandled(msg);		
	}
	
}
public class TestActor 
{
	public static void main(String[] args) throws InterruptedException {
		Props props1 = Props.create(SampleActor.class);
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef sampleActor = system.actorOf(props1, "SampleActor");
		final Inbox inbox = Inbox.create(system);
		send(inbox, sampleActor,5,"hello");
//		send(inbox, sampleActor,3,"hey");
		send(inbox, sampleActor,2,"exit");
		System.out.println(sampleActor.isTerminated());
	}
	static void send(Inbox inbox, ActorRef target,int times, String message) throws InterruptedException
	{
		for(int i=0;i<times;i++)
		{
			Thread.sleep(1000);
			inbox.send(target, message);
		}
	}
}
