package synchronizers;

import java.util.concurrent.CountDownLatch;


public class LatchTest {
	private class Task implements Runnable
	{
		private CountDownLatch slaveLatch;
		private CountDownLatch masterLatch;
		public Task(CountDownLatch slaveLatch, CountDownLatch masterLatch) {
			super();
			this.slaveLatch = slaveLatch;
			this.masterLatch = masterLatch;
		}
		@Override
		public void run() {

			try {
				masterLatch.await();
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" runs the task");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				slaveLatch.countDown();
			}
			
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		int threads = 10;
		CountDownLatch masterLatch = new CountDownLatch(1);
		CountDownLatch slaveLatch = new CountDownLatch(threads);
		Task t = new LatchTest().new Task(slaveLatch, masterLatch);
		for(int i=1;i<=threads;i++)
			new Thread(t).start();
		System.out.println("master thread sleeps for 5 seconds");
		Thread.sleep(5000);
		masterLatch.countDown(); //fires the waiting slave threads
		slaveLatch.await(); //master thread waiting for slave threads to complete
		System.out.println("master thread exiting");
		
	}
	
}
