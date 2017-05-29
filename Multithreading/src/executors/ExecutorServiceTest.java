package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {
	public static void main(String[] args) {
		final int maxThreads = 10;
		Runnable task = new RunnableTask();
		ExecutorService service = Executors.newFixedThreadPool(maxThreads);
		for(int i=0;i<20;i++)
			service.execute(task);
		
		service.shutdown();
	}
	
}
