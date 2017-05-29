package classic;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class Counter
{
	private AtomicInteger counter;
	
	
	public Counter(int value) {
		super();
		this.counter = new AtomicInteger(value);
	}

	void incrementCounter()
	{
		counter.incrementAndGet();
	}
	
	void decrementCounter()
	{
		counter.set(counter.decrementAndGet());
	}
	
	public int getCounterValue()
	{
		return counter.get();
	}
}

class DBConnectionPool
{
	private Counter counter;
	private int poolSize;
	private Queue<String> connectionPool;
	public DBConnectionPool(int poolSize)
	{
		this.counter = new Counter(poolSize);
		this.poolSize = poolSize;
		this.connectionPool = new PriorityQueue<String>();
		for(int i=1;i<=poolSize;i++)
			connectionPool.add("connection"+i);
	}
	
	public String getConnection()
	{
		
		synchronized (counter) {
			while(counter.getCounterValue()==0)
			{
				try {
					counter.wait();
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
			String connection = connectionPool.poll();
			counter.decrementCounter();
			return connection;
		}
	}
	
	public void returnConnection(String connection)
	{
		synchronized (counter) {
			connectionPool.offer(connection);
			counter.incrementCounter();
			counter.notifyAll();
		}
	}
}
public class DBConnPoolTest {
	public static void main(String[] args) {
		final DBConnectionPool pool = new DBConnectionPool(10);
		for(int i=1;i<=20;i++)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					String connection = pool.getConnection();
					System.out.println(Thread.currentThread().getName()+" obtained "+connection);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pool.returnConnection(connection);
					System.out.println(Thread.currentThread().getName()+" returned "+connection);
					
				}
			}).start();
		}
	}
}
