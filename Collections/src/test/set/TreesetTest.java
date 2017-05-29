package test.set;

import java.util.Iterator;
import java.util.TreeSet;

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
	
	
}
public class TreesetTest {
	public static void main(String[] args) {
		TreeSet<Employee> set = new TreeSet<Employee>();
		for(int i=0;i<100;i++)
			set.add(new Employee("emp"+i, i));
		
		Employee testEmp = new Employee("emp50", 50);
		
		System.out.println(set.contains(testEmp));
		System.out.println(set.ceiling(testEmp));
		System.out.println(set.higher(testEmp));
		System.out.println(set.lower(testEmp));
		System.out.println(set.floor(testEmp));
		System.out.println(set.pollFirst()+" , size = "+set.size());
		System.out.println(set.pollLast()+" , size = "+set.size());
		
		for(Iterator<Employee> iter = set.descendingIterator();iter.hasNext();)
			System.out.println(iter.next());
	}
}
