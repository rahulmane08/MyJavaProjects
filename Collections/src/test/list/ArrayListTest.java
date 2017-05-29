package test.list;

import java.util.ArrayList;
import java.util.List;

class Element
{
	private int value;
	private String name;
	public Element(int value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + value;
		return result;
	}*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
}
public class ArrayListTest {
	public static void main(String[] args) {
		List<Element> list = new ArrayList<Element>();
		for(int i=0;i<100;i++)
			list.add(new Element(i, "element"+i));
		
		//operations
		Element elementToSearch = new Element(10,"element10");
		System.out.println(list.contains(elementToSearch));
		System.out.println(list.indexOf(elementToSearch));
	}
}
