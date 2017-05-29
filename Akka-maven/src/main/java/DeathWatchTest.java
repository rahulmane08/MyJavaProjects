import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;


class WatcherActor extends UntypedActor
{

	private ActorRef actorToWatch;
	
	
	public WatcherActor(ActorRef actorToWatch) {
		super();
		this.actorToWatch = actorToWatch;
	}

	@Override
	public void preStart() throws Exception {
		
		super.preStart();
		getContext().watch(actorToWatch);
	}

	@Override
	public void onReceive(Object message) throws Exception 
	{
		if(message.equals("kill"))
		{
			getContext().unwatch(actorToWatch);
			getContext().stop(getSelf());
		}
		else if (message instanceof Terminated) {
			final Terminated t = (Terminated) message;
			
		} 
		
	}
	
}


public class DeathWatchTest {

}
