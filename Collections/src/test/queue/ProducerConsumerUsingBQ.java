package test.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



public class ProducerConsumerUsingBQ {
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(10);
		Thread producer = new Thread(new Producer(bq, "Producer"));
	    Thread consumer = new Thread(new Consumer(bq, "Consumer"));
	    producer.start();
	    consumer.start();
	    
	    Thread.sleep(60*1000);
	    producer.interrupt();
	    consumer.interrupt();
	    producer.join();
	    consumer.join();
	}
}
