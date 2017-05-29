package iterator;

import java.util.Iterator;

public class Dinner  implements Iterable<MenuItem>{
	private MenuItem[] items = new MenuItem[]{new MenuItem("rice"),new MenuItem("dal"),new MenuItem("roti")};

	@Override
	public Iterator<MenuItem> createIterator() {
		// TODO Auto-generated method stub
		return new ArrayIterator(items);
	}
	
	
	
}
