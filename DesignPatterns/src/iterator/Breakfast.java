package iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Breakfast implements Iterable<MenuItem>{
	private ArrayList<MenuItem> items;

	public Breakfast() {
		items = new ArrayList<MenuItem>();
		items.add(new MenuItem("omlette"));
		items.add(new MenuItem("bread"));
		items.add(new MenuItem("tea"));
	}

	@Override
	public Iterator<MenuItem> createIterator() {
		// TODO Auto-generated method stub
		return items.iterator();
	}
	
	
}
