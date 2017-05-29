package list;

import java.util.Arrays;

class DLLNode<T>
{
	T data;
	public DLLNode(T data) {
		this.data = data;
	}
	DLLNode<T> next, prev;
}
public class DoublyLinkedList<T> 
{
	DLLNode<T> start;

	public DoublyLinkedList(DLLNode<T> start) {
		super();
		this.start = start;
	}

	public DoublyLinkedList() {
		super();
	}
	
	public DoublyLinkedList(T[] arr) {
		for(T x: arr)
			insert(x);
	}
	
	public void insert(T data)
	{
		if(start==null)
		{
			start = new DLLNode<>(data);
			return;
		}
		DLLNode<T> curr = start;
		while(curr.next!=null)
			curr=curr.next;
		curr.next = new DLLNode<>(data);
		curr.next.prev = curr;
	}
	
	public int size()
	{
		if(start==null)
			return 0;
		int size = 0;
		DLLNode<T> curr = start;
		while(curr!=null)
		{
			++size;
			curr = curr.next;			
		}
		return size;	
	}
	@Override
	public String toString() {
		Object[] arr = new Object[size()];
		int i =0;
		DLLNode<T> curr = start;
		while(curr!=null)
		{
			arr[i++] = curr.data; 
		}
		return Arrays.toString(arr);
	}
	
}
