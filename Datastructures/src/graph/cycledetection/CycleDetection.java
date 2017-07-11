package graph.cycledetection;

import java.util.HashSet;
import java.util.Stack;

import graph.DisjointSet;
import graph.DisjointSet.Node;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class CycleDetection {
	
	static public <T> boolean detectCycle(Graph<T> graph)
	{
		if(graph==null)
			return false;
		if(graph.isDirected())
			return detectCycleInDG(graph);
		else
			return detectCycleInUDG(graph);
	}
	static private <T> boolean detectCycleInDG(Graph<T> graph)
	{
		HashSet<Long> visited = new HashSet<>();
		Stack<Vertex<T>> stack = new Stack<>();
		for(Vertex<T> curr: graph.getAllVertexes())
			if(detectCycleInDG(curr, visited, stack))
			{
				System.out.println("Cycle detected");
				return true;
			}
				
		System.out.println("No cycles detected");
		return false;
	}
	
	static private <T> boolean detectCycleInDG(Vertex<T> vertex, HashSet<Long> visited, Stack<Vertex<T>> stack)
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
	
	static private <T> boolean detectCycleInUDG(Graph<T> graph)
	{
		DisjointSet<Long> set = new DisjointSet<>();
		for(Vertex<T> v: graph.getAllVertexes())
			set.makeSet(v.getId());
		for(Edge<T> edge: graph.getAllEdges())
		{
			Vertex<T> v1 = edge.getVertex1();
			Vertex<T> v2 = edge.getVertex2();
			
			//find the set representatives
			Node<Long> r1 = set.findSet(v1.getId());
			Node<Long> r2 = set.findSet(v2.getId());
			
			if(r1.equals(r2))
			{
				System.out.println("Cycle detected");
				return true;
			}
			
			set.union(v1.getId(), v2.getId());
		}
		System.out.println("No cycle detected");
		return false;
	}
}
