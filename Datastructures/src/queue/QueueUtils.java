package queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import stack.Stack;

public class QueueUtils {
	public <T> void reversify(Queue<T> queue) {
		if (queue == null)
			return;
		Stack<T> stack = new Stack<>(queue.capacity());
		while (queue.isEmpty())
			stack.push(queue.deque());
		while (stack.isEmpty())
			queue.enqueue(stack.pop());
	}
	/**
	 * 1. populate first window
	 * 2. start with next element in the array.
	 * 3. first check if the max in prev window is outside window:dq.peekFirst() < i - k
	 * 4. then check if the current element is greater than current window elements if yes, pop them out and add current
	 * @param arr
	 * @param k
	 */
	static public void printMaxInSlidingWindowOfSizeK(int[] arr, int k) {
		int n = arr.length;
		Deque<Integer> dq = new LinkedList<>();
		int i;
		for (i = 0; i < k; i++) {
			while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
				dq.pollLast();
			dq.offerLast(i);
		}
		for (; i < n; i++) {
			System.out.println("Window = [" + (i - k) + "], max = " + arr[dq.peekFirst()]);
			while (!dq.isEmpty() && dq.peekFirst() < i - k)
				dq.pollFirst();
			while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
				dq.pollLast();
			dq.offerLast(i);
		}
		System.out.println("Window = [" + (i - k) + "], max = " + arr[dq.peekFirst()]);
	}

	/**
	 *  0: Empty cell
		1: Cells have fresh oranges		
		2: Cells have rotten oranges 
		A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] 
		
		
	 * @param orangeBox
	 * @param m
	 * @param n
	 */
	static public void printTimeToRotOranges(int[][] orangeBox, int m, int n) {
		Queue<Vertex> vertices = new Queue<Vertex>(m * n);
		/** put all rotten orange vertices in the queue to begin with **/
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (orangeBox[i][j] == 2)
					vertices.enqueue(new Vertex(i, j));
		/** enter a Dummy vertex to mark end of current time frame **/
		Vertex dummy = new Vertex();
		vertices.enqueue(dummy);
		int time = 0;
		
		/** bfs over the queue **/
		while (!vertices.isEmpty()) {
			boolean flag = false;
			while (!(vertices.top() == dummy)) {
				Vertex curr = vertices.deque();
				/** right orange **/
				Vertex right = new Vertex(curr.x + 1, curr.y);
				Vertex left = new Vertex(curr.x - 1, curr.y);
				Vertex up = new Vertex(curr.x, curr.y - 1);
				Vertex down = new Vertex(curr.x, curr.y + 1);
				Vertex[] neighbors = new Vertex[] { left, right, up, down };
				for (Vertex sibling : neighbors) {
					if (isValidIndex(m, n, sibling.x, sibling.y) && orangeBox[sibling.x][sibling.y] == 1) {
						if (!flag) {
							flag = true;
							++time;
						}
						orangeBox[sibling.x][sibling.y] = 2;
						vertices.enqueue(sibling);
					}
				}
			}
			vertices.deque();
			if (!vertices.isEmpty())
				vertices.enqueue(dummy);
		}
		System.out.println("Time frame to rot all oranges = " + time);
	}

	static private boolean isValidIndex(int m, int n, int x, int y) {
		return !((x < 0) || (x >= m) || (y < 0) || (y >= n));
	}

	static public void printLargestMultipleOf3(int[] arr) {
		int n = arr.length;
		int[] largestMultiple = new int[arr.length];
		Arrays.sort(arr);
		Queue<Integer> q0 = new Queue<>(arr.length);
		Queue<Integer> q1 = new Queue<>(arr.length);
		Queue<Integer> q2 = new Queue<>(arr.length);
		boolean cantForm = false;
		int sum = 0;
		for (int i : arr) {
			sum += i;
			if (i % 3 == 0)
				q0.enqueue(i);
			else if (i % 3 == 1)
				q1.enqueue(i);
			else
				q2.enqueue(i);
		}
		if (sum % 3 == 1) {
			if (!q1.isEmpty())
				q1.deque();
			else {
				if (!q2.isEmpty()) {
					q2.deque();
					if (!q2.isEmpty())
						q2.deque();
					else
						cantForm = true;
				} else
					cantForm = true;
			}
		} else if (sum % 3 == 2) {
			if (!q2.isEmpty())
				q2.deque();
			else {
				if (!q1.isEmpty()) {
					q1.deque();
					if (!q2.isEmpty())
						q1.deque();
					else
						cantForm = true;
				} else
					cantForm = true;
			}
		}
		if (cantForm) {
			System.out.println("No Largest multiple of 3 cant be formed");
			return;
		}
		List<Queue<Integer>> queues = Arrays.asList(new Queue[] { q0, q1, q2 });
		int i = 0;
		for (Queue<Integer> currQ : queues)
			while (!currQ.isEmpty()) {
				largestMultiple[i++] = currQ.deque();
			}
		Arrays.sort(largestMultiple);
		System.out.print("Largest multiple of 3 = ");
		for (int j = largestMultiple.length - 1; j >= 0; j--)
			System.out.print(largestMultiple[j]);
		System.out.println();
	}
}
