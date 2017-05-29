package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Immutable
{
	private final int value;
	private final List<String> list;
	public Immutable(int value, List<String> list) {
		super();
		this.value = value;
		this.list = list;
	}
	
	public int getValue() {
		return value;
	}
	
	public List<String> getList() {
		List<String> clone = new ArrayList<String>(list);
		return clone;
	}

	@Override
	public String toString() {
		return "Immutable [value=" + value + ", list=" + list + "]";
	}
	
	
}
public class ImmutabilityTest {
	public static void main(String[] args) {
		//construct immutable object
		final Immutable immutable = new Immutable(10, Arrays.asList(new String[]{"Rahul","Mane"}));
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int x = immutable.getValue();
				x++;
				immutable.getList().add("Thread1");
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int x = immutable.getValue();
				x=x*100;
				immutable.getList().add("Thread2");
				
			}
		}).start();
		
		System.out.println(immutable);
	}
}
