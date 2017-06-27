package synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierTest {
	public static void main(String[] args) throws InterruptedException {
		int parties = 3;
		CyclicBarrier barrier = new CyclicBarrier(parties);
		spawnThreads(parties, barrier);
		Thread.sleep(10000);
		barrier.reset();
		spawnThreads(parties, barrier);
	}
	
	private static void spawnThreads(final int noOfParties, final CyclicBarrier barrier)
	{
		for(int i=1;i<=noOfParties;i++)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					String currentThreadname = Thread.currentThread().getName();
					System.out.println(currentThreadname+" waiting for barrier");
					try {
						Thread.sleep(2000);
						barrier.await();
						System.out.println(currentThreadname+" is now executing");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}).start();
		}
	}
}
