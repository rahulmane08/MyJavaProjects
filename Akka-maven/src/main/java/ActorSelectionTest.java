import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.UntypedActor;

class Child extends UntypedActor
{

	@Override
	public void onReceive(Object message) throws Exception {
		if(message.equals("hi"))
			System.out.println("hi said by "+getSender());
		else if(message.equals("greet_brothers"))
			getContext().actorSelection("../*").tell("hi", getSelf());
		else
			unhandled(message);
	}
	
}
class Parent extends UntypedActor
{
	@Override
	public void preStart() throws Exception 
	{		
		super.preStart();
		for(int i=1;i<=3;i++)
		{
			getContext().actorOf(Props.create(Child.class), "child"+i);
		}
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if(message.equals("end"))
			getContext().stop(getSelf());
		else
			unhandled(message);
	}
	
}
public class ActorSelectionTest 
{
	public static void main(String[] args) 
	{
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef parent = system.actorOf(Props.create(Parent.class), "parent");
		Inbox inbox = Inbox.create(system);
		ActorSelection actor = system.actorSelection("/user/app/parent/child1");
		System.out.println(actor);
		actor.tell("greet_brothers", null);
	}
}
