
package graph;

import graph.cycledetection.CycleDetection;
import graph.cycledetection.HamiltonianCycleDetector;
import graph.mst.KruskalMST;
import graph.traversal.GraphTraversal;

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
		
		
		graph = createGraph(false);		
		HamiltonianCycleDetector.printHamiltonianCycle(graph);
		
		graph = createGraph(true);	
		CycleDetection.detectCycle(graph);
		
		graph = createGraph(false);	
		CycleDetection.detectCycle(graph);
		
		graph = createWeightedGraph(false);
		KruskalMST.printMST(graph);
		
//		TarjanArticulationPoint tp = new TarjanArticulationPoint();
//		System.out.println("Articulation points = "+tp.getArticulationPoints(graph));
	}

	private static Graph<String> createGraph(boolean directed) {
		Graph<String> graph;
		graph = new Graph<>(directed);
		Vertex<String> A = new Vertex<>(1);
		Vertex<String> B = new Vertex<>(2);
		Vertex<String> C = new Vertex<>(3);
		Vertex<String> D = new Vertex<>(4);
		
		A.setData("A");
		B.setData("B");
		C.setData("C");
		D.setData("D");
		
		graph.addEdge(A, B, 1);
		graph.addEdge(B, C, 1);
		graph.addEdge(C, D, 1);
		graph.addEdge(D, A, 1);
		return graph;
	}
	
	private static Graph<String> createWeightedGraph(boolean directed) {
		Graph<String> graph;
		graph = new Graph<>(directed);
		Vertex<String> A = new Vertex<>(1);
		Vertex<String> B = new Vertex<>(2);
		Vertex<String> C = new Vertex<>(3);
		Vertex<String> D = new Vertex<>(4);
		Vertex<String> E = new Vertex<>(5);
		Vertex<String> F = new Vertex<>(6);
		
		A.setData("A");
		B.setData("B");
		C.setData("C");
		D.setData("D");
		E.setData("E");
		F.setData("F");
		
		graph.addEdge(A, B, 3);
		graph.addEdge(A, D, 1);
		graph.addEdge(B, D, 3);
		graph.addEdge(B, C, 1);
		graph.addEdge(C, D, 1);
		graph.addEdge(C, E, 5);
		graph.addEdge(C, F, 4);
		graph.addEdge(D, E, 6);
		graph.addEdge(E, F, 2);
		return graph;
	}
}
