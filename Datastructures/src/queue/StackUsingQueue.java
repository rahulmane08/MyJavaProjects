package queue;

public class StackUsingQueue<T> {
	private Queue<T> queue;

	public T pop()
	{
		if(queue.isEmpty())
			throw new RuntimeException("Stack is empty , nothing to pop");
		return queue.deque();
	}
	public void push(T elem)
	{
		int size = queue.size();
		queue.enqueue(elem);
		if(size>0)
		{
			for(int i=0;i<size-1;i++)
			{
				T curr = queue.deque();
				queue.enqueue(curr);
			}
		}
		
	}
}
