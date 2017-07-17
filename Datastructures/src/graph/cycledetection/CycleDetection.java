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

	/**
	 *     idea is to do DFS and pass a stack to the dfs method
	 *     if the path from the current node ends with a sink node then 
	 *     detectCycleInDG(Vertex<T> vertex, HashSet<Long> visited, Stack<Vertex<T>> stack)
	 *     return false
	 *     if there is a cycle, the method will definitely comeback to the root node in which case 
	 *     we dont proceed with the recursion but check in the path stack.
	 *     Also we will have to pop out the current node from stack before recursion unwinds the
	 *     current method call
	 * 
	 * @param graph
	 * @return
	 */
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
	
	/**
	 * 1. create a disjoint set of all vertexes in graph
	 * 2. iterate over all the edges and start putting connected nodes in a single set.
	 * 3. if the two nodes being added belong to same set, ie have the same set representative, then there is a cycle.
	 * 
	 * @param graph
	 * @return
	 */
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
