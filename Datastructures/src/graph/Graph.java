package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> 
{
	private List<Edge<T>> allEdges;
	boolean isDirected = false;
	private Map<Long,Vertex<T>> allVertexes;
	public Graph(boolean isDirected) {
		super();
		this.isDirected = isDirected;
		this.allEdges = new ArrayList<>();
		this.allVertexes = new HashMap<>();
	}
	
	public void addEdge(long id1, long id2, int weight)
	{
		Vertex<T> v1 = allVertexes.get(id1);
		Vertex<T> v2 = allVertexes.get(id2);
		if(v1==null)
		{
			v1 = new Vertex<>(id1);
			allVertexes.put(id1, v1);
		}
		if(v2==null)
		{
			v2 = new Vertex<>(id2);
			allVertexes.put(id2, v2);
		}
		
		Edge<T> edge = new Edge<>(v1, v2, isDirected, weight);
		v1.addAdjacentVertex(edge, v2);		
		if(!isDirected)
			v2.addAdjacentVertex(edge, v1);
		allEdges.add(edge);
	}
	
	public void addVertex(Vertex<T> vertex)
	{
		if(allVertexes.containsKey(vertex.getId()))
			return;
		allVertexes.put(vertex.getId(), vertex);
		if(vertex.getEdges()!=null && vertex.getEdges().size()>0)
			for(Edge<T> edge: vertex.getEdges())
				allEdges.add(edge);
	}
	
	public Vertex<T> getVertex(long id)
	{
		return allVertexes.get(id);
	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}

	public boolean isDirected() {
		return isDirected;
	}

	public Collection<Vertex<T>> getAllVertexes() {
		return allVertexes.values();
	}

	public void addEdge(Vertex<T> a, Vertex<T> b, int weight) {
		allVertexes.put(a.id, a);
		allVertexes.put(b.id, b);
		addEdge(a.id, b.id, weight);		
	}
	
	
}
