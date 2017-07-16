package graph;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import graph.cycledetection.CycleDetection;
import graph.traversal.GraphTraversal;

public class GraphUtils 
{
	private static final int INF = Integer.MAX_VALUE;
	static public <T> Graph<T> transpose(Graph<T> graph)
	{
		if(graph==null)
			return null;
		if(!graph.isDirected)
			return graph;
		Graph<T> transpose = new Graph<>(graph.isDirected);
		for(Edge<T> edge:graph.getAllEdges())
			graph.addEdge(edge.getVertex2().getId(), edge.getVertex1().getId(), edge.getWeight());
		return transpose;				
	}	
	static private <T> boolean areConnected(Vertex<T> v1, Vertex<T> v2, Graph<T> graph,HashSet<Long> visited)
	{
		if(graph!=null)
		{			
			for(Vertex<T> v: v1.getAdjacentVertexes())
				if(!visited.contains(v.getId()))
				{
					visited.add(v.getId());
					if(v.equals(v2))
						return true;
					else
						return areConnected(v, v2, graph,visited);
				}
					
		}
		
		return false;		
	}
	/**
	 * 1. check if no cycles
	 * 2. start from vertex and check if all vertices are traced.
	 * @param graph
	 * @return
	 */
	static public <T> boolean checkIfGraphIsTree(Graph<T> graph)
	{
		if(graph!=null)
		{
			boolean cyclePresent = CycleDetection.detectCycle(graph);
			if(cyclePresent)
				return false;
			HashSet<Long> visited = new HashSet<>();
			Vertex<T> start = graph.getAllVertexes().iterator().next();
			GraphTraversal.dfsUtil(start, visited);
			return visited.size()==graph.getAllVertexes().size();
			
		}
		return false;
	}
	
	static public <T> void printAllPaths(Graph<T> graph, Vertex<T> source, Vertex<T> dest)
	{
		if(graph==null)
			return;		
		
		printAllPathsUtil(graph, source, dest, new LinkedHashSet<>());
	}
	
	static private <T> void printAllPathsUtil(Graph<T> graph, Vertex<T> source, Vertex<T> dest,HashSet<Long> visited)
	{			
		visited.add(source.getId());
		if(source.equals(dest))
		{
			System.out.print("Found a path ");
			visited.forEach(v->{
				System.out.print(graph.getVertex(v)+" ");
			});
			System.out.println();
		}
		else
		{
			
			List<Vertex<T>> adjacentVertexes = source.getAdjacentVertexes();		
			for(Vertex<T> adj:adjacentVertexes)
				if(!visited.contains(adj.getId()))
					printAllPathsUtil(graph, adj, dest, visited);
		}
		visited.remove(source.getId());			
	}
	
	static public <T> int[][] getAdjacencyMatrix(Graph<T> graph)
	{
		if(graph==null)
			return null;
		int V = graph.getAllEdges().size();
		int [][] adj = new int[V][V];
		for(int i=0;i<V;i++)
			for(int j=0;j<V;j++)
				adj[i][j] = INF;
		
		for(Edge<T> e: graph.getAllEdges())
		{
			int i = (int) e.getVertex1().getId();
			int j = (int) e.getVertex2().getId();
			adj[i][j]=e.getWeight();
			
			if(!graph.isDirected)
				adj[j][i]=e.getWeight();
		}
		return adj;
	}
	
}
