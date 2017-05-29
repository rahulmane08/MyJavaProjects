package queue;
import stack.Stack;

public class QueueOperations 
{
	public <T> void reversify(Queue<T> queue)
	{
		if(queue==null)
			return;
		Stack<T> stack = new Stack<>(queue.capacity());
		while(queue.isEmpty())
			stack.push(queue.deque());
		while(stack.isEmpty())
			queue.enqueue(stack.pop());
	}
}
