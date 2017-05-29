package stack;

public class ReverseStack<T> {
	private Object[] array;
	private int top;
	
	public ReverseStack(int capacity)
	{
		array =  new Object[capacity];
		top= -1;
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
	public void push(T elem)
	{
		if(elem==null)
			return;
		if(isFull())
			throw new RuntimeException("Stack is full , cant add "+elem);		
					
	}
	
}
