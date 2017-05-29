package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable
{
	private BlockingQueue<String> queue;
	public Producer(BlockingQueue<String> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("producing 1");
			queue.put("1");
			Thread.sleep(10000);
			System.out.println("producing 2");
			queue.put("2");
			Thread.sleep(10000);
			System.out.println("producing 3");
			queue.put("3");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class Consumer implements Runnable
{
	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(Thread.currentThread().getName()+" consuming");
			String element = queue.take();
			System.out.println(Thread.currentThread().getName()+" consumed "+element);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
public class ProducerConsumerUsingBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
		Thread producer = new Thread(new Producer(queue));
		Thread consumer1 = new Thread(new Consumer(queue));
		Thread consumer2 = new Thread(new Consumer(queue));
		Thread consumer3 = new Thread(new Consumer(queue));
		consumer1.setName("consumer1");
		consumer2.setName("consumer2");
		consumer3.setName("consumer3");
		consumer1.start();
		consumer2.start();
		consumer3.start();
		Thread.sleep(5000);
		producer.start();
		
		
	}
}
