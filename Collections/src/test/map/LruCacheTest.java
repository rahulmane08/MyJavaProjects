package test.map;

import java.util.LinkedHashMap;

class LruCache extends LinkedHashMap<String, String>{

	private int capacity;
	
	
	
	public LruCache(int capacity) {
		super(capacity,0.75f,true);
		this.capacity = capacity;
	}



	@Override
	protected boolean removeEldestEntry(
			java.util.Map.Entry<String, String> eldest) {
		
		return size()>capacity;
	}
}

public class LruCacheTest
{
	public static void main(String[] args) {
		LruCache cache = new LruCache(10);
		for(int i=0;i<30;i++)
		{
			cache.put("key"+i, "value"+i);
			System.out.println(cache.keySet());
		}
	}
}
