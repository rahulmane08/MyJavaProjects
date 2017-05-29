import java.util.ArrayList;


public class StaticMonitor {
	private static ArrayList<String> list = new ArrayList<String>();
	
	public static boolean add(String elem)
	{
		synchronized (StaticMonitor.class) {
			return list.add(elem);
			
		}
	}
	
	public String get(int index)
	{
		synchronized (StaticMonitor.class) {
			return list.get(index);
		}
	}
}
