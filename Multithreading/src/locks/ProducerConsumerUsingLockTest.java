package locks;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PCQueue<T>
{
	private PriorityQueue<T> queue;
	private volatile int MAX_CAPACITY;
	public PCQueue(int capacity)
	{
		MAX_CAPACITY = capacity;
		queue = new PriorityQueue<T>(MAX_CAPACITY);
	}
	
	//create a lock and 2 conditions for isEmpty or isFull
	Lock lock = new ReentrantLock(true);
	Condition isFull = lock.newCondition();
	Condition isEmpty = lock.newCondition();
	
	public void put(T element) throws InterruptedException{
		lock.lock();
		try{
			while(queue.size()==MAX_CAPACITY)
				isFull.await();
			
			boolean isAdded = queue.offer(element);
			if(isAdded)
				isEmpty.signalAll();
		}		
		finally{
			lock.unlock(); 	
		}
	}
	
	public T take() throws InterruptedException
	{
		lock.lock();
		T element = null;
		try{
			while(queue.size()==0)
				isEmpty.await();
			
			element =  queue.poll();
			
			isFull.signalAll();
			
			return element;
		}
		finally{
			lock.unlock();
		}
		
	}
}


public class ProducerConsumerUsingLockTest {
	public static void main(String[] args) throws InterruptedException {
		final PCQueue<String> queue = new PCQueue<String>(10);
		queue.put("ELEMENT");
		
		Runnable consumer = ()-> {
			while(!Thread.currentThread().isInterrupted()){
				try {
					String element = queue.take();
					System.out.println(Thread.currentThread().getName()+" takes "+element);
					Thread.sleep(2000);
					queue.put(element);
					
				} catch (InterruptedException e) {					
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				
			}
		};
		
		Thread consumer1 = new Thread(consumer,"consumer1");		
		Thread consumer2 = new Thread(consumer,"consumer2");
		consumer1.start();
		consumer2.start();
		Thread.sleep(20*1000);
		consumer1.interrupt();
		consumer2.interrupt();
		
	}
}
