
class StackFullException extends Exception
{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Stack is full";
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return toString();
	}
}
class StackEmptyException extends Exception
{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Stack is empty";
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return toString();
	}
}
public class GenericStack<E> {
	
	private E[] elements;
	private int size;
	private int filledIndex;
	
	@SuppressWarnings("unchecked")
	public GenericStack(int size) {
		super();
		this.size = size;
		elements  =(E[]) new Object[this.size];
		filledIndex = -1;
	}
	
	public void push(E element) throws StackFullException
	{
		if(filledIndex==size)
			throw new StackFullException();
		elements[++filledIndex] = element;
	}
	public E pop() throws StackEmptyException
	{
		if(filledIndex==-1)
			throw new StackEmptyException();
		E elem = elements[filledIndex];
		elements[filledIndex] = null;
		filledIndex--;
		return elem;
	}
	
	
}
class TestGenericStack
{
	public static void main(String[] args) throws StackEmptyException, StackFullException {
		GenericStack<Integer> stack= new GenericStack<Integer>(10);
		
		for(int i=0;i<10;i++)
			stack.push(i);
		for(int j=0;j<12;j++)
			System.out.println(stack.pop());
	}
}
