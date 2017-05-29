
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.ActorSystem;
import akka.dispatch.Filter;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.dispatch.Recover;
import akka.japi.Function;

public class FuturesTest {
	public static OnSuccess<Integer> printIntegerOnSuccess = new OnSuccess<Integer>() {		
		@Override
		public void onSuccess(Integer i) throws Throwable {
			System.out.println("result ="+i);
			
		}
	};
	
	public static OnFailure printError = new OnFailure() {
		
		@Override
		public void onFailure(Throwable t) throws Throwable {
			System.out.println("Error = "+t.getMessage());
			
		}
	};
	public static OnSuccess<Boolean> printBooleanOnSuccess = new OnSuccess<Boolean>() {		
		@Override
		public void onSuccess(Boolean i) throws Throwable {
			System.out.println("result ="+i);
			
		}
	};
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		final ExecutionContext ec = system.dispatcher();
		
		Future<String> f1 = Futures.future(new Callable<String>() {

			public String call() throws Exception {
				Thread.sleep(5000);
				return "Akka-test";
			}
		}, ec); 
		
		//mapping
		Future<Integer> f2 = f1.map(new Mapper<String, Integer>() {
			@Override
			public Integer apply(String parameter) {				
				return parameter!=null?parameter.length():0;
			}
		}, ec);
		
		f2.onSuccess(printIntegerOnSuccess, ec);
		
		//filter
		Future<Integer> f3 = f2.filter(Filter.filterOf(new Function<Integer, Boolean>() {

			public Boolean apply(Integer i) throws Exception {				
				return i%2==0;
			}
		}), ec);
		
		f3.onSuccess(printIntegerOnSuccess, ec);
		f3.onFailure(printError, ec);
		
		
		//piping
		java.util.List<Future<Integer>> listOfFutures = new ArrayList<Future<Integer>>();
		listOfFutures.add(f2);
		listOfFutures.add(f3);
		Future<Iterable<Integer>> combined = Futures.sequence(listOfFutures, ec);
		Future<Integer> f4 = combined.map(new Mapper<Iterable<Integer>, Integer>() {
			@Override
			public Integer apply(Iterable<Integer> parameter) {
				int sum = 0;
				for(int i: parameter)
					sum += i;
				return sum;
			}
		}, ec);
		f4.onSuccess(printIntegerOnSuccess, ec);
		f4.onFailure(printError, ec);
		
		//fallbacks
		Future<Integer> f5 = Futures.failed(new IllegalAccessException());
		Future<Integer> f6 = Futures.failed(new IllegalAccessException());
		Future<Integer> f7 = f5.fallbackTo(f6).fallbackTo(f4);
		f7.onSuccess(printIntegerOnSuccess, ec);
		f7.onFailure(printError, ec);
		
		//exception handling
		Future<Integer> f8 = f7.recover(new Recover<Integer>() {

			@Override
			public Integer recover(Throwable t) throws Throwable {
				if(t instanceof Exception)
					return 0;
				else
				 throw t;
			}

			
		}, ec);
		f8.onSuccess(printIntegerOnSuccess, ec);
	}

	
}
