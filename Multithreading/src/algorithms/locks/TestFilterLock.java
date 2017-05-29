package algorithms.locks;

public class TestFilterLock {
	public static void main(String[] args) {
		int n=3;
		final FilterLock filterLock = new FilterLock(n);
		Thread [] threads = new Thread[n];
		Runnable job = new Runnable() {
			
			@Override
			public void run() {
				filterLock.lock();
				System.out.println("thread"+Thread.currentThread().getName()+" executing CS");
				filterLock.unlock();
			}
		};
		for(int i=0;i<n;i++)
		{
			Thread t = new Thread(job,""+i);
			threads[i]=t;
		}
		for(int i=0;i<3;i++)
			threads[i].start();
		
	}
}
