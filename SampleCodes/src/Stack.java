import java.util.Arrays;
import java.util.EmptyStackException;

// Can you spot the "memory leak"?
public class Stack implements Cloneable{
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		Object result = elements[--size];
		elements[size] = null;
		return result;
	}

	/**
	 * Ensure space for at least one more element, roughly doubling the capacity
	 * each time the array needs to grow.
	 */
	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
	
	@Override
	protected Stack clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Stack clonedStack = (Stack)super.clone();
		clonedStack.elements = elements.clone();
		return clonedStack;
	}

	@Override
	public String toString() {
		return "Stack [elements=" + Arrays.toString(elements) + ", size="
				+ size + "]";
	}
	
	
}




