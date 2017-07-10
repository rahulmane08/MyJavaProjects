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
	
	static private Comparator<Edge> weightComparator = new Comparator<Edge>() {		
		@Override
		public int compare(Edge o1, Edge o2) {
			Integer w1 = o1.getWeight();
			Integer w2 = o2.getWeight();
			return w1.compareTo(w2);
		}
	};
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
}
