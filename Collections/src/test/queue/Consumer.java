package test.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	BlockingQueue<String> queue;
	String name;
	public Consumer(BlockingQueue<String> queue, String name) 
	{
		super();
		this.queue = queue;
		this.name = name;
	}
	@Override
	 public void run() {		      
	      System.out.println(name+" Started");
	      while(!Thread.currentThread().isInterrupted())
	      {		    	  
	    	  try {	    		
				System.out.println(name+" consumed = "+queue.take());
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println(name+" is interrupted");
				Thread.currentThread().interrupt();				
			}
	      }
	 }

}
