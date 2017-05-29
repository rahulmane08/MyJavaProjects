package list;

import java.util.Arrays;

class Node<T>{
	public T data;
	public Node<T> next;
	public Node(T data) {
		super();
		this.data = data;		
	}
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
	
}
public class LinkedList<T>
{
	
	Node<T> start;
	
	
	
	public LinkedList() {
		super();
	}


	public LinkedList(Node<T> start) {
		super();
		this.start = start;
	}
	public LinkedList(T[] arr) {
		for(T data:arr)
			insert(data);
	}


	public void insert(T data)
	{
		if(start==null)
		{
			start = new Node(data);
			return;
		}
		Node<T> curr= start;
		while(curr.next!=null)
			curr = curr.next;
		curr.next = new Node<>(data);
	}
	
	public int length()
	{		
		return length(this.start);
	}
	private int length(Node n)
	{	
		if(n==null)
			return 0;
		return 1+length(n.next);
	}
	public void traverse()
	{
		if(start==null)
			return;
		Node tempNode = start;
		while(tempNode!=null)
		{
			System.out.print(tempNode.data+" ");
			tempNode = tempNode.next;
		}
		System.out.println();
	}


	public int size() {
		if(start==null)
			return 0;
		int size = 0;
		for(Node<T> curr= start; curr!=null;curr=curr.next)
			++size;
		return size;
	}


	public T get(int index) {
		if(index>size())
			return null;
		Node<T> curr= start;
		for(int i=1;i<=index;i++)
			curr = curr.next;
		return curr.data;
	}
	
	public boolean remove(T data)
	{
		if(start==null)
			return false;
		if(start.data ==  data)
		{
			start = start.next;
			return true;
		}
		Node<T> curr = start;
		while(curr.next!=null && curr.next.data == data)
			curr = curr.next;
		if(curr.next!=null)
		{
			//node found
			curr.next = curr.next.next;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		Object[] arr = new Object[size()];
		int i =0;
		Node<T> curr = start;
		while(curr!=null)
		{
			arr[i++] = curr.data; 
			curr = curr.next;
		}
		return Arrays.toString(arr);
	}
}
