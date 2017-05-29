package test.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

class Employee implements Comparable<Employee>{
	private String name;
	private Integer empID;
	public Employee(String name, Integer empID) {
		super();
		this.name = name;
		this.empID = empID;
	}
	@Override
	public int compareTo(Employee e) {
		int compare = this.empID.compareTo(e.empID);
		return compare;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", empID=" + empID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empID == null) ? 0 : empID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Employee other = (Employee) obj;
		if (empID == null) {
			if (other.empID != null)
				return false;
		} else if (!empID.equals(other.empID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}

class ValueComparator implements Comparator<Map.Entry<String, Employee>>{

	@Override
	public int compare(Entry<String, Employee> e1,
			Entry<String, Employee> e2) {
		Employee emp1 = e1.getValue();
		Employee emp2 = e2.getValue();
		return emp1.compareTo(emp2);
	}
	
}
public class HashmapTest {
	public static void main(String[] args) {
		Map<String, Employee> map = new HashMap<String, Employee>();
		for(int i=0;i<10;i++)
			map.put("emp"+i,new Employee("emp"+i, i));
		System.out.println(map);
		List<Map.Entry<String, Employee>> list = new ArrayList<Map.Entry<String,Employee>>(map.entrySet());
		Collections.sort(list,new ValueComparator());
		map.clear();
		for(Entry<String, Employee> entry:list)
			map.put(entry.getKey(), entry.getValue());
		
		System.out.println(map);
		
		//containsValue test CANT BE DONE ON AN OBJECT, EVEN IF EQUALS AND HASCODE ARE OVERRIDDEN
		Employee testEmp = new Employee("emp50", 50);
		System.out.println(map.containsValue(testEmp));
		
		
		
	}
}
