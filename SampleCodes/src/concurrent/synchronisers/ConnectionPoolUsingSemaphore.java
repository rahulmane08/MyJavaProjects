package concurrent.synchronisers;


import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class ConnectionPool
{
	private Queue<String> pool;
	private Semaphore sem;
	public ConnectionPool(int size) {
		super();
		pool = new PriorityQueue<String>();
		for(int i=1;i<=size;i++)
			pool.add("Connection"+i);			
		this.sem = new Semaphore(size);
	}
	
	public String getConnection()
	{
		String connection = null;
		try {
			System.out.println(Thread.currentThread().getName()+" trying to get the connection");
			sem.acquire();
			connection = pool.poll();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			sem.release();
			e.printStackTrace();
		}
		finally
		{
			
			return connection;
		}
		
	}
	
	public void returnConnection(String connection)
	{
		try{
			boolean returned = pool.offer(connection);
			if(returned)
				sem.release();
		}
		finally
		{
			System.out.println(Thread.currentThread().getName()+" returned the connection");
		}
	}
	
}

class Task implements Runnable
{
	private ConnectionPool conPool;
	public Task(ConnectionPool conPool) {
		// TODO Auto-generated constructor stub
		this.conPool = conPool;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String connection  = conPool.getConnection();
		System.out.println(Thread.currentThread().getName()+" obtained the connection = "+connection);
		try {
			Thread.sleep(10000);
			conPool.returnConnection(connection);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class ConnectionPoolUsingSemaphore {
	public static void main(String[] args) {
		ConnectionPool conPool = new ConnectionPool(3);
		Task task = new Task(conPool);
		Thread t1 = new Thread(task,"worker1");
		Thread t2 = new Thread(task,"worker2");
		Thread t3 = new Thread(task,"worker3");
		t1.start();
		t2.start();
		t3.start();
		
		Thread t4 = new Thread(task,"worker4");
		Thread t5 = new Thread(task,"worker5");
		t4.start();
		t5.start();
		
	}
	
	
	
}
