package graph.mst;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import graph.DisjointSet;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.DisjointSet.Node;

public class KruskalMST 
{
	
	static private Comparator<Edge> weightComparator = new WeightComparator();
	static public <T> void printMST(Graph<T> graph)
	{
		List<Edge<T>> mstEdges = getMST(graph);
		for(Edge<T> e:mstEdges)
		{
			System.out.println(e);
		}
	}

	public static <T> List<Edge<T>> getMST(Graph<T> graph) {
		if(graph==null)
			return null;
		
		List<Edge<T>> edges = graph.getAllEdges();
		Collections.sort(edges, weightComparator);
		DisjointSet<Long> set = new DisjointSet<>();
		for(Vertex<T> v: graph.getAllVertexes())
			set.makeSet(v.getId());
		for(Iterator<Edge<T>> iter=graph.getAllEdges().iterator();iter.hasNext();)
		{
			Edge<T> edge = iter.next();
			Vertex<T> v1 = edge.getVertex1();
			Vertex<T> v2 = edge.getVertex2();
			
			//find the set representatives
			Node<Long> r1 = set.findSet(v1.getId());
			Node<Long> r2 = set.findSet(v2.getId());
			
			if(r1.equals(r2))
			{
				iter.remove(); // remove that edge which connects node belonging to same set
			}
			
			set.union(v1.getId(), v2.getId());
		}
		return edges;
	}
	
	public static void main(String[] args) {
		Graph<String> graph = new Graph<>(false);
		Vertex<String> A = new Vertex<>(1);
		Vertex<String> B = new Vertex<>(2);
		Vertex<String> C = new Vertex<>(3);
		Vertex<String> D = new Vertex<>(4);
		
		A.setData("A");
		B.setData("B");
		C.setData("C");
		D.setData("D");
		
		graph.addEdge(A, B, 1);
		graph.addEdge(A, C, 3);
		graph.addEdge(D, B, 1);
		graph.addEdge(D, C, 1);
		graph.addEdge(B, C, 2);
		
		KruskalMST.printMST(graph);
	}
}
