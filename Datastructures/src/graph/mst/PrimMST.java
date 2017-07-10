package graph.mst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class PrimMST {
	static private Comparator<Edge> weightComparator = new WeightComparator();
	
	
	static public <T> void printMST(Graph<T> graph)
	{
		List<Edge<T>> mstEdges = getMST(graph);
		for(Edge<T> e:mstEdges)
		{
			System.out.println(e);
		}
	}
	private static <T> List<Edge<T>> getMST(Graph<T> graph) {
		Map<Vertex<T>, Edge<T>> minEdgesByVertex = new HashMap<>();
		
		
		return null;
	}
}
