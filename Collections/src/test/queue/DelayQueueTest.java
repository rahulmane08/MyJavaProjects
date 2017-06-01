package test.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayTasks implements Delayed
{
	private String taskName;
	private long delay;
	
	public DelayTasks(String taskName, long delayInSecs) {
		super();		
		this.delay = System.currentTimeMillis()+delayInSecs*1000;
		this.taskName = taskName;
	}
	@Override
	public int compareTo(Delayed o) {
		long delay1 = this.getDelay(TimeUnit.MILLISECONDS);
		long delay2 = o.getDelay(TimeUnit.MILLISECONDS);
		if(delay1<delay2)
			return -1;
		else if(delay1>delay2)
			return 1;
		else
			return 0;
	}
	@Override
	public long getDelay(TimeUnit unit) {	
		long timeRemaining = delay - System.currentTimeMillis();
		return unit.convert(timeRemaining, TimeUnit.MILLISECONDS) ;
	}
	@Override
	public String toString() {
		return "DelayTasks [taskName=" + taskName + "]";
	}
	
	
}
public class DelayQueueTest {
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Delayed> queue = new DelayQueue<Delayed>();
		for(int i=1;i<=5;i++)
		{
			int delay = (int)(Math.random()*10);
			String task = "task"+i;
			System.out.println("added "+task+" delay "+delay+" secs");
			queue.offer(new DelayTasks(task, delay));
		}
			
		
		while(!queue.isEmpty())
			System.out.println(queue.take());
	}
}
