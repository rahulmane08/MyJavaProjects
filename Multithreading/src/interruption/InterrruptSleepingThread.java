package interruption;

import java.util.ArrayList;
import java.util.List;

public class InterrruptSleepingThread {
	public static void main(String[] args) throws InterruptedException {
		final List<String> list = new ArrayList<String>();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int counter = 1;
				while(!Thread.currentThread().isInterrupted())
				{
					System.out.println(counter+" square = "+Math.pow(counter++, 2));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
//						Thread.currentThread().interrupt();
					}
					list.add(Thread.currentThread().getName()+":elem"+counter);
					
				}
			}
		});
		
		
		
		t1.start();
		Thread.sleep(5000);
		System.out.println("main thread interrupting the worker thread");
		t1.interrupt();
		System.out.println(t1.isInterrupted());
		System.out.println(list);
	}
}
