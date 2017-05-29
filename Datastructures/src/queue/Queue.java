package queue;

public class Queue<T> 
{
	private Object[] arr;
	private int front=-1,rear=-1;
	
	
	public Queue(int capacity)
	{
		arr = new Object[capacity];
	}
	
	public void enqueue(T elem)
	{		
		if(isFull())
			throw new RuntimeException("Queue is full");		
		rear = (rear+1)%capacity();
		arr[rear] = elem;
		if(front==-1)
			front = rear;
	}
	public T deque()
	{
		if(isEmpty())
			throw new RuntimeException("Queue is empty");
		T elem = (T) arr[front];
		if(front==rear)
		{
			front=rear=-1;
		}
		else
			front = (front+1)%capacity();
		return elem;
	}
	
	public int capacity()
	{
		return arr.length;
	}
	
	public int size()
	{			
		return Math.abs(rear-front)+1;
	}
	
	public boolean isFull()
	{
		if((rear+1)%capacity() == front)
			return true;
		return false;
	}
	public boolean isEmpty()
	{
		return (front==-1);
	}

	public T top() {
		
		return (T) arr[front];
	}
}
