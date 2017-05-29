package synchronizers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Part
{
	private double weight;

	Part(double d) {
		super();
		this.weight = d;
	}

	@Override
	public String toString() {
		return "Part [weight=" + weight + "kgs]";
	}
	
}
class Producer extends Thread
{
	private BlockingQueue<Part> queue;
	
	Producer(BlockingQueue<Part> queue) {
		super();
		this.queue = queue;
	}

	public void produce() throws InterruptedException{
		Part part = new Part(Math.floor(Math.random()*10));
		queue.put(part);
		System.out.println("Producer adds "+part);
	}
	
	@Override
	public void run() {
		for(int i=0;i<20;i++)
		{
			try {
				Thread.sleep(2000);
				produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread
{
	private BlockingQueue<Part> queue;

	Consumer(BlockingQueue<Part> queue) {
		super();
		this.queue = queue;
	}
	public void consume() throws InterruptedException
	{
		Part part = queue.take();
		System.out.println(Thread.currentThread().getName()+" consumed "+part);
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++)
		{
			try {
				
				consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
public class ProducerConsumer {
	public static void main(String[] args) {
		BlockingQueue<Part> queue = new LinkedBlockingQueue<Part>();
		Producer producer = new Producer(queue);
		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);
		producer.start();
		consumer1.start();
		consumer2.start();
	}
}
