package test.map;

import java.util.LinkedHashMap;

class LruCache<K, V> extends LinkedHashMap<K, V>{
	private static final long serialVersionUID = 1L;
	private int capacity;
	
	class CacheValue<V>
	{
		
	}
	
	class CacheKey<K>
	{
		
	}
	
	public LruCache(int capacity) {
		super(capacity,0.75f,true);
		this.capacity = capacity;
	}
	
	
	@Override
	protected boolean removeEldestEntry(
			java.util.Map.Entry<K, V> eldest) {
		
		return size()>capacity;
	}
}

public class LruCacheTest
{
	public static void main(String[] args) {
		LruCache<String,String> cache = new LruCache<>(10);
		
		for(int i=0;i<10;i++)
			cache.put("key"+i, "value"+i);
		
		for(int i=0;i<6;i++)
			cache.get("key"+i);
		
		for(int i=10;i<30;i++)
		{
			cache.put("key"+i, "value"+i);			
			System.out.println(cache.keySet());
		}
	}
}
