package synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Connection
{
	private String name;

	
	public Connection(String name) {
		super();
		this.name = name;
	}


	@Override
	public String toString() {
		return "Connection [name=" + name + "]";
	}
	
}
class ConnectionPool
{
	
	private Semaphore semaphore;
	private List<Connection> connections;
	public ConnectionPool(int size) {
		super();
		
		semaphore = new Semaphore(size);
		connections = new ArrayList<Connection>(size);
		for(int i=0;i<size;i++)
			connections.add(new Connection("connection"+i));
	}
	
	public Connection getConnection()
	{
		Connection con = null;
		try {
			semaphore.acquire();
			con = connections.remove(0);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" obtained "+con);
		return con;
	}
	
	public void returnConnection(Connection con)
	{
		if(con!=null)
		{
			connections.add(con);
			semaphore.release();
			System.out.println(Thread.currentThread().getName()+" released "+con);
		}
	}
	
}
public class SemaphoreTest {
	public static void main(String[] args) throws InterruptedException {
		final ConnectionPool pool = new ConnectionPool(3);
		Runnable task = new Runnable() {			
			@Override
			public void run() {
				
				try {
					List<Connection> cons = new ArrayList<Connection>();
					for(int i=0;i<3;i++)
						cons.add(pool.getConnection());
					Thread.sleep(20*1000);
					for(int i=0;i<cons.size();i++)
						pool.returnConnection(cons.remove(0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread t1 = new Thread(task);
		t1.start();
		Thread.sleep(2000);
		Thread t2 = new Thread(task);
		t2.start();
	}
}
