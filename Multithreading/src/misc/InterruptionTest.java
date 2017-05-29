package misc;

public class InterruptionTest {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted())
				{
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName()+" executing");
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName()+" interrupted");
						//THIS CATCH WILL SWALLOW THE INTERRUPTION
//						Thread.currentThread().interrupt(); //hence re interrupt the thread, if this is commented the 
															//while condition will not fail and wil continue to run infinitely
					}
				}
			}
		};
		
		Thread t = new Thread(task);
		t.start();
		Thread.sleep(10000);
		System.out.println("Main thread interrupting the thread after 10seconds");
		t.interrupt();
	}
	
}
