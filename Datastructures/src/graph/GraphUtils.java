package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import list.ListNode;

public class GraphUtils 
{
	static public <T> void bfs(Graph<T> graph, T start)
	{
		
		if(!check(graph, start))
			return;
		
		HashSet<ListNode<T>> visited = new HashSet<>();
		Queue<ListNode<T>> queue = new LinkedList<>();
		ListNode<T> curr = new ListNode<T>(start);
		queue.offer(curr);	
		visited.add(curr);
		
		while(!queue.isEmpty())
		{
			curr = queue.poll();	
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
	
	static private <T> boolean check(Graph<T> graph, T start)
	{
		if(graph==null)
			return false;	
	
		ListNode<T> head = new ListNode<T>(start); 
		list.LinkedList<T> edges = graph.getConnectedNodes(head);
		if(edges==null)
			return false;
		return true;
	}
	
	static public <T> void dfs(Graph<T> graph)
	{
		if(graph==null)
			return;
		HashSet<ListNode<T>> visited = new HashSet<>();		
		for(ListNode<T> vertex : graph.getVertices())
			if(!visited.contains(vertex))
				dfsUtil(graph, vertex, visited);
	}
	
	static private <T> void dfsUtil(Graph<T> graph, ListNode<T> start, HashSet<ListNode<T>> visited)
	{
		System.out.println(start);
		visited.add(start);
		
		list.LinkedList<T> currList = graph.getConnectedNodes(start);
		for(ListNode<T> temp = currList.start;temp!=null;temp=temp.next)
		{				
			if(!visited.contains(temp))
			{
				visited.add(temp);
				dfsUtil(graph, temp, visited);
			}
		}
	}
}
