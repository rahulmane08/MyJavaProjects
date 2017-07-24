package test.queue;

import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
	BlockingQueue<String> queue;	
	String name;
	int elemNumber=0;
	public Producer(BlockingQueue<String> queue, String name) 
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
		        	 String elem = (String) ("elem"+(elemNumber++)); 
		        	 System.out.println(name+" putting an element = "+elem);
		        	 queue.put(elem);
		        
		      } catch (Exception ex) {
		    	  System.out.println(name+" is interrupted");
				  Thread.currentThread().interrupt();
		      }
	      }
	 }
	
}
