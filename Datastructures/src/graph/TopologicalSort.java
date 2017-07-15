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
	
	public static <T> Stack<Vertex<T>> topSort(Graph<T> graph)
	{
<<<<<<< HEAD
		if(graph==null || !graph.isDirected)
			return;
=======
		if(graph==null)
			return null;
>>>>>>> branch 'master' of https://github.com/rahulmane08/MyJavaProjects.git
		HashSet<Long> visited = new HashSet<>();
		Stack<Vertex<T>> stack = new Stack<>();
		//insert into the stack by the visit times. TOPOLOGICAL SORT
		for(Vertex<T> vertex:graph.getAllVertexes())
			if(!visited.contains(vertex.getId()))
				topologicalSortUtil(vertex, visited, stack);
		
<<<<<<< HEAD
		System.out.println("topological sort");
		while(!stack.isEmpty())
		{
			System.out.println(stack.pop().getData());
		}
=======
		System.out.println("topological sort = "+stack);
		return stack;
>>>>>>> branch 'master' of https://github.com/rahulmane08/MyJavaProjects.git
	}
	
	public static <T> void allTopSortPaths(Graph<T> graph)
	{
		if(graph==null || !graph.isDirected)
			return;
		
		HashSet<Long> visited = new HashSet<>();
		Stack<Vertex<T>> stack = new Stack<>();
		
		
	}
	
	private static <T> void allTopSortPathsUtil(Graph<T> graph, HashSet<Long> visited, Stack<Vertex<T>> stack)
	{
		for(Vertex<T> v: graph.getAllVertexes())
		{
			if(!visited.contains(v.getId()) && v.getInDegree()==0)
			{				
				
				for(Vertex<T> adj:v.getAdjacentVertexes())
					adj.decrementInDegree();
				stack.push(v);
				visited.add(v.getId());
				allTopSortPathsUtil(graph, visited, stack);
				
				visited.remove(v.getId());
				stack.pop();
				for(Vertex<T> adj:v.getAdjacentVertexes())
					adj.incrementInDegree();	
				
				System.out.println(stack);
			}
		}
	}
	
	public static void main(String[] args) {
		Graph<String> graph = new Graph<>(true);
		Vertex<String> A = new Vertex<>(1);
		Vertex<String> B = new Vertex<>(2);
		Vertex<String> C = new Vertex<>(3);
		Vertex<String> D = new Vertex<>(4);
		Vertex<String> E = new Vertex<>(5);
		A.setData("A");
		B.setData("B");
		C.setData("C");
		D.setData("D");
		E.setData("E");
		
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		graph.addVertex(E);
		
		graph.addEdge(A, B, 1);
		graph.addEdge(A, C, 1);
		graph.addEdge(A, D, 1);
		graph.addEdge(B, C, 1);
		graph.addEdge(D, C, 1);
		graph.addEdge(C, E, 1);
//		topSort(graph);
		allTopSortPaths(graph);
	}
}
