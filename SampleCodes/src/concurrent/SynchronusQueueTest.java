package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

class Consumer1 implements Runnable
{
	private BlockingQueue<String> queue;
	public Consumer1(BlockingQueue<String> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			
			try {
				System.out.println("consumer1 taking");
				queue.take();
				Thread.sleep(3000);
				System.out.println("consumer1 adding");queue.add("1");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
class Consumer2 implements Runnable
{
	private BlockingQueue<String> queue;

	public Consumer2(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			
			try {
				System.out.println("consumer2 adding");
				queue.add("1");
				Thread.sleep(3000);
				System.out.println("consumer2 taking");queue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}
public class SynchronusQueueTest {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new SynchronousQueue<String>();
		new Thread(new Consumer2(queue)).start();
		new Thread(new Consumer1(queue)).start();
		
	}
}
