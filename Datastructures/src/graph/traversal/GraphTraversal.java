package graph.traversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import graph.Graph;
import graph.Vertex;

public class GraphTraversal 
{
	static public <T> void bfs(Graph<T> graph)
	{
		if(graph==null)
			return;
		System.out.println("BFS Traversal============");
		Queue<Vertex<T>> queue = new LinkedList<>();
		HashSet<Long> visited = new HashSet<>();
		
		for(Vertex<T> vertex: graph.getAllVertexes())
		{
			if(!visited.contains(vertex.getId()))
			{
				queue.offer(vertex);
				visited.add(vertex.getId());
				
				while(!queue.isEmpty())
				{
					Vertex<T> curr = queue.poll();
					System.out.println(curr.getData());
					for(Vertex<T> adjacentVertex: curr.getAdjacentVertexes())
					{
						if(!visited.contains(adjacentVertex.getId()))
						{
							queue.offer(adjacentVertex);
							visited.add(adjacentVertex.getId());
						}
					}
				}
			}			
		}
	}
	
	static public <T> HashSet<Long> dfs(Graph<T> graph)
	{
		if(graph==null)
			return null;
		System.out.println("DFS Traversal============");
		HashSet<Long> visited = new HashSet<>();
		for(Vertex<T> vertex: graph.getAllVertexes())
			if(!visited.contains(vertex.getId()))
				dfsUtil(vertex, visited);
		return visited;		
	}
	
	static public <T> void dfsUtil(Vertex<T> vertex,HashSet<Long> visited)
	{
		visited.add(vertex.getId());
		System.out.println(vertex.getData());
		for(Vertex<T> adjacentVertex: vertex.getAdjacentVertexes())
			if(!visited.contains(adjacentVertex.getId()))
				dfsUtil(adjacentVertex, visited);
	}
}
