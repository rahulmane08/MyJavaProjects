package misc;

import java.util.concurrent.atomic.AtomicInteger;

class Task implements Runnable
{
	int state=0;
	AtomicInteger atomicState=new AtomicInteger(0);

	@Override
	public void run() {
		for(int i=0;i<100000;i++)
		{
			
			atomicState.incrementAndGet();
			state++;
			
			System.out.println(Thread.currentThread().getName()+" : state= "+state);
			System.out.println(Thread.currentThread().getName()+" : atomicState= "+atomicState);
		}
	}
	
}
public class AtomicVariableTest {
	public static void main(String[] args) {
		Task t = new Task();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
	}
}
