import java.util.Iterator;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {
	@Override
	public void preStart() {
		// create the greeter actor
		final ActorRef greeter = getContext().actorOf(
				Props.create(Greeter.class), "greeter");
		// tell it to perform the greeting
		greeter.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Greeter.Msg.DONE) {
			// when the greeter is done, stop this actor and with it the
			// application
			Iterable<ActorRef> childrens = getContext().getChildren();
			if(childrens!=null)
			{
				Iterator<ActorRef> childItr = childrens.iterator();
				while(childItr.hasNext())
				{
					ActorRef child = childItr.next();
					System.out.println(child.toString());
				}
			}
			getContext().stop(getSelf());
		} else
			unhandled(msg);
	}
}

class Greeter extends UntypedActor {
	public static enum Msg {
		GREET, DONE;
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Msg.GREET) {
			System.out.println("Hello World!");
			getSender().tell(Msg.DONE, getSelf());
		} else
			unhandled(msg);
	}
}