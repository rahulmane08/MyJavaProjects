package compareto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author rahul
 * the generic collections use 
 */

class Element implements Comparable<Element>
{
	private Integer data;
	private String id;
	
	public Element(Integer data, String id) {
		super();
		this.data = data;
		this.id = id;
	}
	@Override
	public int compareTo(Element e) {
		// TODO Auto-generated method stub
		
		return id.compareTo(e.id);
	}
	@Override
	public boolean equals(Object e) {
		if(!(e instanceof Element))
			return false;
		Element elem = (Element)e;
		return (id == elem.id);
	}
   
	
	
	
}
public class CompareToVsEquals {
	public static void main(String[] args) {
		Element elem1 = new Element(1, "id1");
		TreeSet<Element> tree = new TreeSet<Element>();
		tree.add(elem1);
		//treeset never users equals() to check for equality test
		System.out.println(tree.contains(new Element(1, "id1")));
		System.out.println(tree.contains(new Element(1, "id2")));
	}
	
}
