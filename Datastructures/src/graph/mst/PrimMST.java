package graph.mst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class PrimMST {
	static private final Integer INF = Integer.MAX_VALUE;
	static private Comparator<PrimNode> weightComparator = new Comparator<PrimMST.PrimNode>() {

		@Override
		public int compare(PrimNode o1, PrimNode o2) {
			Integer w1=o1.getEdgeWeight();
			Integer w2=o2.getEdgeWeight();
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
	
	class PrimNode<T>
	{
		Vertex<T> vertex;
		int edgeWeight;
		public PrimNode(Vertex<T> vertex, int edgeWeight) {
			super();
			this.vertex = vertex;
			this.edgeWeight = edgeWeight;
		}
		public Vertex<T> getVertex() {
			return vertex;
		}
		public int getEdgeWeight() {
			return edgeWeight;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PrimNode other = (PrimNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (vertex == null) {
				if (other.vertex != null)
					return false;
			} else if (!vertex.equals(other.vertex))
				return false;
			return true;
		}
		private PrimMST getOuterType() {
			return PrimMST.this;
		}
		
	}
	private static <T> List<Edge<T>> getMST(Graph<T> graph) {
		Map<Vertex<T>, Edge<T>> minEdgesByVertex = new HashMap<>();
		PriorityQueue<PrimNode<T>> pq = new PriorityQueue<>(weightComparator);
		HashSet<PrimNode<T>> currVertexMinWeights = new HashSet<>();
		
		boolean start=false;
		for(Vertex<T> v:graph.getAllVertexes())
		{
			
		}
		
		
		
		return null;
	}
}
