package iterator;

import java.util.Iterator;

public class ArrayIterator implements Iterator<MenuItem> {

	private MenuItem[] items;
	int position = 0;
	
	public ArrayIterator(MenuItem[] items) {
		super();
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		if(items == null || position>=items.length || items[position]==null)
			return false;
		return true;
	}

	@Override
	public MenuItem next() {
		// TODO Auto-generated method stub
		
		return items[position++];
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
