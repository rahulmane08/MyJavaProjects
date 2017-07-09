package graph.cycledetection;

import java.util.HashSet;
import java.util.Stack;

import graph.Graph;
import graph.Vertex;

public class CycleDetectionInDG {
	static public <T> void detectCycleInDG(Graph<T> graph)
	{
		if(graph==null || !graph.isDirected())
			return;
		HashSet<Long> visited = new HashSet<>();
		Stack<Vertex<T>> stack = new Stack<>();
		for(Vertex<T> curr: graph.getAllVertexes())
			if(detectCycleInDG(curr, visited, stack))
			{
				System.out.println("Cycle detected");
				return;
			}
				
		System.out.println("No cycles detected");
	}
	
	static public <T> boolean detectCycleInDG(Vertex<T> vertex, HashSet<Long> visited, Stack<Vertex<T>> stack)
	{
		stack.add(vertex);
		visited.add(vertex.getId());
		for(Vertex<T> adj: vertex.getAdjacentVertexes())
			if(!visited.contains(vertex.getId()))
				return detectCycleInDG(adj, visited, stack);
			else
				return stack.contains(adj);
		stack.remove(vertex);
		return false;		
	}
}
