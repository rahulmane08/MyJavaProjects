package collections;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapIterator {
	public static void main(String[] args) throws InterruptedException {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		final Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(iter.hasNext())
				{
					Entry<String, String> entry = iter.next();
					System.out.println(entry.getKey()+":"+entry.getValue());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		Thread.sleep(2000);
		//modify the map , however this wont be read by the reader thread spawned above
		map.put("key4", "value4");
		
	}
}
