package test.map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapTest {
	public static void main(String[] args) {
		final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("elem0", "value0");
		map.put("elem1", "value1");
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=10;i++)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int x = i%2;
					map.put("elem"+x, "T1:value"+Math.random());
					System.out.println("T1 updating "+x+"="+map);
				}
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=10;i++)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int x = (i+1)%2;
					map.put("elem"+x, "T2:value"+Math.random());
					System.out.println("T2 updating "+x+"="+map);
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
	
	}
}
