package iterator;

import java.util.Iterator;

public interface Iterable<T> {
	public Iterator<T> createIterator();
}
