package test.map;

import java.util.IdentityHashMap;

class Element
{
	private Integer id;

	
	public Integer getId() {
		return id;
	}

	public Element(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
public class IdentityHashMapTest {
	public static void main(String[] args) {
		IdentityHashMap<Element, Integer> map = new IdentityHashMap<Element, Integer>();
		Element e = new Element(99);
		map.put(e, e.getId());
		System.out.println(map.get(e));
		System.out.println(map.get(new Element(99)));
	}
}
