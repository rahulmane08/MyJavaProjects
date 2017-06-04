package synchronizers;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class DBConnectionPool {
	private Semaphore semaphore;
	private int poolSize;
	private Queue<String> connectionPool;

	public DBConnectionPool(int poolSize) {
		this.semaphore = new Semaphore(poolSize);
		this.poolSize = poolSize;
		this.connectionPool = new PriorityQueue<String>();
		for (int i = 1; i <= poolSize; i++)
			connectionPool.add("connection" + i);
	}

	public String getConnection() {
		String connection = null;
		try {
			semaphore.acquire();
			connection = connectionPool.poll();
			
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}
		finally
		{
			semaphore.release();
		}
		return connection;
	}

	public void returnConnection(String connection) {
		connectionPool.offer(connection);
		semaphore.release();		
	}
}

public class DBConnPoolUsingSemaphore {
	public static void main(String[] args) {
		final DBConnectionPool pool = new DBConnectionPool(10);
		for (int i = 1; i <= 20; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					String connection = pool.getConnection();
					System.out.println(Thread.currentThread().getName()
							+ " obtained " + connection);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pool.returnConnection(connection);
					System.out.println(Thread.currentThread().getName()
							+ " returned " + connection);

				}
			}).start();
		}
	}
}
