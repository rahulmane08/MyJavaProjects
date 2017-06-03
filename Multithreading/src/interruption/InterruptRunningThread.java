package interruption;

public class InterruptRunningThread 
{
	public static void main(String[] args) throws InterruptedException {
		Runnable r = () -> {
			int counter = 1;
			while(!Thread.currentThread().isInterrupted())
				System.out.println(counter+" square = "+Math.pow(counter++, 2));
		};
		Thread t = new Thread(r, "worker");
		t.start();
		Thread.sleep(5*1000);
		t.interrupt();
		
		System.out.println("main thread exiting");
	}
}
