package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = new CompletableFuture<>();
		
		Runnable blockingRunnable = ()-> {
			System.out.println(Thread.currentThread().getName()+" trying to get from cf");
			String result = null;
			
			//result = cf.getNow("DEFAULT");//using getNow it wont block but instead get the default value if the future isnt completed
			
			try {
				result = cf.get(); //t1 blocks until some other thread completes the future in this case its main thread. 
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
			System.out.println("Result = "+result);
			System.out.println(Thread.currentThread().getName()+" exiting");
		};
		
		//1. CompletableFuture.get() / complete() test
		Thread t1 = new Thread(blockingRunnable,"Worker");
		t1.start();
		
		Thread.sleep(10*1000);
		cf.complete("HELLO"); //t1 will now unblock and print this result
		//cf.completeExceptionally(new RuntimeException("Main thread threw and exception")); ////t1 will now unblock but get() will throw ExecutionException wrapping up this exception
		t1.join();
		
		
		//2: creating completablefutures using supply and run methods
		//2.1: runAsync() takes a runnable and hence has no return value and hence the return is Void.
		CompletableFuture<Void> voidCf = CompletableFuture.runAsync(()->{
			try {
				System.out.println(Thread.currentThread().getName()+" is sleeping"); //ForkJoinPool.commonPool-worker-9 is sleeping
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).thenRun(()->System.out.println(Thread.currentThread().getName()+"  thread resuming")); 
		voidCf.get();
		 
		//2.2: supplyAsync() method will return a value 
		CompletableFuture<Double> powerCf = CompletableFuture.supplyAsync(()->power(3,2))
																	.thenApply(r->r*r*Math.PI);
		System.out.println("Main thread gets value = "+powerCf.get());
		
		
		System.out.println("main thread exiting");
	}
	
	
	public static Double power(double A, double B) 
	{
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Math.pow(A, B);
	}
}
