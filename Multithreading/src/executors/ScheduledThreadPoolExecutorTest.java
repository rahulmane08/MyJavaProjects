package executors;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("========================");
				System.out.println(Thread.currentThread()+" executed the task");
				
			}
		};
		
		for(int i=0;i<20;i++)
			executor.schedule(task, 2, TimeUnit.SECONDS);
		
		System.out.println("================================");
		for(int i=0;i<20;i++)
			executor.scheduleAtFixedRate(task,0, 2, TimeUnit.SECONDS);
	}
}
