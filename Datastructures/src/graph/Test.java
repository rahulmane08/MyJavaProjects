
package graph;

import graph2.traversal.GraphTraversal;

public class Test 
{
	public static void main(String[] args) 
	{
		Graph<String> graph = new Graph<>(true);
		Vertex<String> v1 = new Vertex<>(1);
		Vertex<String> v2 = new Vertex<>(2);
		Vertex<String> v3 = new Vertex<>(3);
		Vertex<String> v4 = new Vertex<>(4);
		Vertex<String> v5 = new Vertex<>(5);
		v1.setData("A");
		v2.setData("B");
		v3.setData("C");
		v4.setData("D");
		v5.setData("E");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(5, 1, 1);
		graph.addEdge(2, 3, 1);
		graph.addEdge(4, 3, 1);
		
		
		GraphTraversal.bfs(graph);
		GraphTraversal.dfs(graph);
	}
}
