package synchronizers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Task implements Callable<String>
{
	private int time;
	
	
	public Task(int time) {
		super();
		this.time = time;
	}


	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+" executing the task");
		Thread.sleep(this.time*1000);
		return Thread.currentThread().getName()+" task";
	}
	
}
public class FutureTaskTest {
	public static void main(String[] args) {
		Task t1 = new Task(4);
		Task t2 = new Task(8);
		Task t3 = new Task(10);
		Task t4 = new Task(15);
		
		List<Task> tasks = Arrays.asList(new Task[]{t1,t2,t3,t4});
		List<FutureTask<String>> futureTasks = new ArrayList<FutureTask<String>>();
		ExecutorService service = Executors.newCachedThreadPool();
		for(Task t:tasks)
			futureTasks.add((FutureTask<String>)service.submit(t));
		
		for(FutureTask<String> ft: futureTasks)
			try {
				
					System.out.println(ft.get(4,TimeUnit.SECONDS));
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} catch (ExecutionException e) {				
				e.printStackTrace();
			} catch (TimeoutException e) {
				
				e.printStackTrace();
				ft.cancel(true);
			}
			
		futureTasks.clear();
		
		service.shutdown();
		
	}
}