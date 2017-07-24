package test.queue;

import java.util.concurrent.BlockingQueue;

class Producer<T> implements Runnable {
	BlockingQueue<T> queue;	
	String name;
	int elemNumber=0;
	public Producer(BlockingQueue<T> queue, String name) 
	{
		super();
		this.queue = queue;		
		this.name = name;
	}

	@Override
	 public void run() {
	      System.out.println("Producer Started");
	      while(!Thread.currentThread().isInterrupted())
	      {
		      try {
		        	 Thread.sleep(1000);
		        	T elem = (T) ("elem"+(elemNumber++)); 
		        	 System.out.println(name+" putting an element = "+elem);
		        	 queue.put(elem);
		        
		      } catch (Exception ex) {
		    	  System.out.println(name+" is interrupted");
				  Thread.currentThread().interrupt();
		      }
	      }
	 }
	
}
