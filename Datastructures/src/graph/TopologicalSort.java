package graph;

import java.util.HashSet;
import java.util.Stack;

public class TopologicalSort 
{
	public static <T> void topologicalSortUtil(Vertex<T> vertex, HashSet<Long> visited, Stack<Vertex<T>> stack)
	{
		visited.add(vertex.getId());
		for(Vertex<T> adjVertex: vertex.getAdjacentVertexes())
			if(!visited.contains(adjVertex.getId()))
				topologicalSortUtil(adjVertex, visited, stack);
		stack.push(vertex);
	}
	
	public static <T> void topSort(Graph<T> graph)
	{
		if(graph==null)
			return;
		HashSet<Long> visited = new HashSet<>();
		Stack<Vertex<T>> stack = new Stack<>();
		//insert into the stack by the visit times. TOPOLOGICAL SORT
		for(Vertex<T> vertex:graph.getAllVertexes())
			if(!visited.contains(vertex.getId()))
				topologicalSortUtil(vertex, visited, stack);
		
		System.out.println("topological sort");
		while(!stack.isEmpty())
		{
			System.out.println(stack.pop());
		}
	}
}
