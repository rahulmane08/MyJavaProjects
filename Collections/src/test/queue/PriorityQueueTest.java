package test.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Student implements Comparable<Student>
{
	private int age;
	private String name;
	private int rollNo;
	public Student(int age, String name, int rollNo) {
		super();
		this.age = age;
		this.name = name;
		this.rollNo = rollNo;
	}
	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + ", rollNo=" + rollNo
				+ "]";
	}
	@Override
	public int compareTo(Student o) {
		
		return this.name.compareTo(o.name);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
}
class RollComparator implements Comparator<Student>
{

	@Override
	public int compare(Student s1, Student s2) {
		
		return new Integer(s1.getRollNo()).compareTo(new Integer(s2.getRollNo()));
	}
	
}

class ReversifyComparator implements Comparator<Student>
{
	private Comparator<Student> originalComparator;

	ReversifyComparator(Comparator<Student> originalComparator) {
		super();
		this.originalComparator = originalComparator;
	}

	@Override
	public int compare(Student o1, Student o2) {
		
		return -1*originalComparator.compare(o1, o2);
	}
	
}

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<Student> pQueue = new PriorityQueue<Student>();
		addToQueue(pQueue);		
		System.out.println(pQueue);
		
		pQueue = new PriorityQueue<Student>(20,new RollComparator());
		addToQueue(pQueue);
		System.out.println(pQueue);
		
		pQueue = new PriorityQueue<Student>(20,new ReversifyComparator(new RollComparator()));
		addToQueue(pQueue);
		System.out.println(pQueue);
	}
	
	private static void addToQueue(Queue<Student> queue)
	{
		for(int i=0;i<20;i++)
			queue.offer(new Student(20, "student"+i, i));
		
	}
}
