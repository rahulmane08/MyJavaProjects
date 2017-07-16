package graph.shortestpath;

import graph.Graph;
import graph.GraphUtils;
import graph.Vertex;
import utils.Utils;

public class FloydWarshallShortestPath 
{
	private static final int INF = Integer.MAX_VALUE;
	
	public static <T> int[][] getShortestPaths(Graph<T> graph)
	{
		if(graph==null)
			return null;
		
		int[][] distance = GraphUtils.getAdjacencyMatrix(graph);
		int[][] path = new int[distance.length][distance.length];
		int V = graph.getAllVertexes().size();
		for(int i=0;i<V;i++)
			for(int j=0;j<V;j++)
				if(distance[i][j]!=INF && i!=j)
					path[i][j]=i;
				else
					path[i][j]=-1;
		
		for(int k=0;k<V;k++)
			for(int i=0;i<V;i++)
				for(int j=0;j<V;j++)
				{
					if(distance[i][k]==INF || distance[j][k]==INF)
						continue;
					if(distance[i][j]>distance[i][k]+distance[j][k])
					{
						distance[i][j]=distance[i][k]+distance[j][k];
						path[i][j]=path[k][j];
					}
				}
		
		for(int i=0;i<V;i++)
			if(distance[i][i]<0)
				throw new RuntimeException("Graph has a negative weight cycle and hence solution not possible");
		
		
		return path;
		
	}
	
	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>(true);
		Vertex<Integer> v0 = new Vertex<>(0);
		Vertex<Integer> v1 = new Vertex<>(1);
		Vertex<Integer> v2 = new Vertex<>(2);
		Vertex<Integer> v3 = new Vertex<>(3);
		
		graph.addEdge(0, 1, 2);
		graph.addEdge(1, 2, 10);
		graph.addEdge(2, 3, 2);
		graph.addEdge(0, 3, 2);
		
		int V = graph.getAllVertexes().size();
		Utils.print(new FloydWarshallShortestPath().getShortestPaths(graph),V , V);
	}
}
