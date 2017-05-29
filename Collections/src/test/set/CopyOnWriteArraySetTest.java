package test.set;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetTest {
	public static void main(String[] args) {
		final CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
		for(int i=0;i<10;i++)
			set.add("elem"+i);
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				set.add("Thread1 elem");
				try{
					Thread.sleep(10000);
				}
				catch(InterruptedException ie){
					ie.printStackTrace();
				}
				System.out.println(set);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				set.add("Thread2 elem");
				try{
					Thread.sleep(5000);
				}
				catch(InterruptedException ie){
					ie.printStackTrace();
				}
				System.out.println(set);
			}
		});
		t1.start();
		t2.start();
		
		
	}
}
