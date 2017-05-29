package stack;

public class Stack<T> {
	private Object[] array;
	private int top;
	
	public Stack(int capacity)
	{
		array =  new Object[capacity];
		top= -1;
	}
	
	public T top()
	{
		if(isEmpty())
			throw new RuntimeException("Stack is empty , nothing to pop");
		return (T) array[top];
	}
	public boolean isEmpty()
	{
		if(top==-1)
			return true;
		return false;
	}
	
	public boolean isFull()
	{
		if(array!=null && array.length-1==top)
			return true;
		return false;
	}
	public T pop()
	{
		if(isEmpty())
			throw new RuntimeException("Stack is empty , nothing to pop");
		return (T)array[top--];
	}
	
	public void push(T elem)
	{
		if(isFull())
			throw new RuntimeException("Stack is full , cant add "+elem);
		array[++top]=elem;
	}
	
	public int size()
	{
		return top+1;
	}

	
}
