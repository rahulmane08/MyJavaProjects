package concurrent.synchronisers;

import java.util.concurrent.CountDownLatch;


public class LatchTest {
	private static CountDownLatch endLatch;
	private static CountDownLatch startLatch;
	
	
	class Task implements Runnable
	{
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try
			{
				System.out.println(Thread.currentThread().getName()+" trying to execute the task");
				startLatch.await();
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()+" executing the task");
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
			finally
			{
				endLatch.countDown();
			}
			
		}		
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		startLatch = new CountDownLatch(1);
		final int nThreads = 10;
		endLatch = new CountDownLatch(nThreads);
		
		for(int i=0;i<nThreads;i++)
		{
			Thread.sleep(2000);
			Thread t = new Thread(new LatchTest().new Task(),"WorkerThread"+i);
			t.start();
		}
		startLatch.countDown();
		System.out.println("Main Thread waiting for the worker threads to finish");
		endLatch.await();
		System.out.println("Main Thread exiting");
	}
	
	
	
	
}
