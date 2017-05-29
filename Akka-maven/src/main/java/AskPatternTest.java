import java.util.ArrayList;
import java.util.Iterator;

import scala.concurrent.Future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.pattern.AskTimeoutException;
import akka.pattern.Patterns;


class Doubler extends UntypedActor
{

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Integer)
		{
			Thread.sleep(2000);
			Integer i = (Integer)msg;
			System.out.println("doubling = "+i);						
			getSender().tell(i*2, getSelf());
		}
		
	}
	
}

class Tripler extends UntypedActor
{

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Integer)
		{
			Thread.sleep(5000);
			Integer i = (Integer)msg;			
			System.out.println("tripling = "+i);		
			getSender().tell(i*3, getSelf());
		}
		else
			unhandled(msg);
	}
	
}
class Printer extends UntypedActor
{

	@Override
	public void onReceive(Object msg) throws Exception 
	{
		System.out.println(msg);
		if(msg instanceof Integer)
		{
			Integer i = (Integer)msg;
			System.out.println("recieved "+i);
		}		
		else
			unhandled(msg);
	}
	
}
public class AskPatternTest 
{
	public static void main(String[] args) 
	{
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef doubler = system.actorOf(Props.create(Doubler.class),"doubler");
		ActorRef tripler = system.actorOf(Props.create(Tripler.class),"tripler");
		ActorRef printer = system.actorOf(Props.create(Printer.class),"printer");
		final ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();
		futures.add(Patterns.ask(doubler, new Integer(9), 5000));
		futures.add(Patterns.ask(tripler, new Integer(4), 5000));
		final Future<Iterable<Object>> aggregate = Futures.sequence(futures,system.dispatcher());
		final Future<Integer> transformed = aggregate.map(new Mapper<Iterable<Object>, Integer>() {
													@Override
													public Integer apply(Iterable<Object> coll) {
														final Iterator<Object> it = coll.iterator();
														final Integer x = (Integer)it.next();
														return x;
													}
											}, system.dispatcher());
		Patterns.pipe(transformed, system.dispatcher()).to(printer);
	}
}
