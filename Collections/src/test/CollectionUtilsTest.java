package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Element implements Comparable<Element>{
	private Integer id;

	public Element(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Element [id=" + id + "]";
	}

	@Override
	public int compareTo(Element o) {
		
		return this.id.compareTo(o.id);
	}
	
}
public class CollectionUtilsTest {
	public static void main(String[] args) {
		Element[] e = new Element[20];
		for(int i=0;i<20;i++)
		{
			e[i]=new Element(i);
		}
		List<Element> list = new ArrayList<Element>();
		
		addToCollection(list, e);
		
		Element testElement = new Element(100);
		System.out.println(list);
		System.out.println(Collections.max(list)); //19
		System.out.println(Collections.min(list)); //19
		Collections.replaceAll(list, list.get(10), testElement); //9 replaced by 100
		System.out.println(list);
		Collections.sort(list);//100 becomes the last element
		System.out.println(list);
		System.out.println(Collections.binarySearch(list, testElement)); //index=19
		list.add(testElement); // two elements with id=100
		System.out.println(Collections.frequency(list, testElement));// returns 2
		Collections.rotate(list, 3);
		System.out.println(list);
		Collections.shuffle(list);
		System.out.println(list);
		
	}
	
	public static <T> void addToCollection(Collection<T> collection, T[] elements){
		for(T e: elements)
			collection.add(e);
	}
}
