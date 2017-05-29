package test.map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapConcurrencyTest {
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("key", "value");
	}
}
