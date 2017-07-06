package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import list.ListNode;

public class GraphUtils 
{
	static public <T> void bfs(Graph<T> graph, T start)
	{
		if(graph==null)
			return;	
	
		ListNode<T> head = new ListNode<T>(start); 
		list.LinkedList<T> edges = graph.getConnectedNodes(head);
		if(edges==null)
			return;
		
		HashSet<ListNode<T>> visited = new HashSet<>();
		Queue<ListNode<T>> queue = new LinkedList<>();
		queue.offer(head);	
		visited.add(head);
		while(!queue.isEmpty())
		{
			ListNode<T> curr = queue.poll();	
			System.out.println(curr);
			list.LinkedList<T> currList = graph.getConnectedNodes(curr);
			
			for(ListNode<T> temp = currList.start;temp!=null;temp=temp.next)
			{				
				if(!visited.contains(temp))
				{
					visited.add(temp);
					queue.offer(temp);
				}
			}
				
		}
	}
}
