package graph.mst;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
class PrimNode
{
	private final Long vertexID;
	private int edgeWeight;
	public PrimNode(Long vertexID) {
		super();
		this.vertexID = vertexID;		
	}
	public Long getVertexID() {
		return vertexID;
	}
	public int getEdgeWeight() {
		return edgeWeight;
	}
	
	public void setEdgeWeight(int edgeWeight) {
		this.edgeWeight = edgeWeight;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vertexID == null) ? 0 : vertexID.hashCode());
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
		if (vertexID == null) {
			if (other.vertexID != null)
				return false;
		} else if (!vertexID.equals(other.vertexID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PrimNode [vertexID=" + vertexID + ", edgeWeight=" + edgeWeight
				+ "]";
	}
	
	
	
}
public class PrimMST {
	static private final Integer INF = Integer.MAX_VALUE;
	static private Comparator<PrimNode> weightComparator = new Comparator<PrimNode>() {

		@Override
		public int compare(PrimNode o1, PrimNode o2) {
			Integer w1=o1.getEdgeWeight();
			Integer w2=o2.getEdgeWeight();
			return w1.compareTo(w2);
		}
	};
	
	
	public static <T> void printMST(Graph<T> graph) {
		Map<Vertex<T>, Edge<T>> minEdgesByVertex = new HashMap<>();
		
		//two structures to query for a vertex in constant time and get min in log(V) time.
		PriorityQueue<PrimNode> pq = new PriorityQueue<>(weightComparator);
		HashMap<Long, PrimNode> map = new HashMap<>();
		
		boolean start=false;
		//add all vertices to map and priority queue with the first vertex being the start node with weight=0 and rest being INF
		for(Vertex<T> v:graph.getAllVertexes())
		{
			PrimNode primNode = new PrimNode(v.getId());
			if(!start)
			{
				primNode.setEdgeWeight(0);
				start=true;
			}
			else
				primNode.setEdgeWeight(INF);;		
			map.put(v.getId(), primNode);
			pq.add(primNode);
		}
		
		while(!pq.isEmpty())
		{
			PrimNode primNode= pq.poll();
			Vertex<T> vertex = graph.getVertex(primNode.getVertexID());
			if(minEdgesByVertex.containsKey(vertex))
			{
				System.out.println(minEdgesByVertex.get(vertex));
			}
			else
			{
				for(Edge<T> e:vertex.getEdges())
				{				
					Vertex<T> neighbor = e.getVertex2();					
					PrimNode neighborPrim = map.get(neighbor.getId());
					if(e.getWeight()<neighborPrim.getEdgeWeight())
					{					
						//if the edge weight is less that the current prim weight then update the prim weight and add back to PQ.
						pq.remove(neighborPrim);
						neighborPrim.setEdgeWeight(e.getWeight());
						pq.add(neighborPrim);
						minEdgesByVertex.put(neighbor, e);
					}
				}
			}
			
		}
		
	}
}
