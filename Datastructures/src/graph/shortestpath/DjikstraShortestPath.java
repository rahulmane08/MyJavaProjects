package graph.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
/**
 * Given a source vertex this algorithm tries to find all the shorted paths from source to all other vertexes.
 * 
 * @author rahul
 *
 */
public class DjikstraShortestPath 
{
	private static final int INF = Integer.MAX_VALUE;
	
	private static Comparator<Entry<Long, Integer>> weightComparator = new Comparator<Map.Entry<Long,Integer>>() {
		
		@Override
		public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
			
			return o1.getValue().compareTo(o2.getValue());
		}
	};
	/*
	 * 1. for each vertex set the minweight as INF, for start vertex set it as 0 and parents(start,NULL)
	 * 2. for each edge, find the neighbor and if its not in unvisited then skip
	 * 3. else and compute if u+uv<v'
	 * 4. Find min node
	 * 
	 */
	static public <T> Map<List<Vertex<T>>, Integer> getShortestPaths(Graph<T> graph, Vertex<T> start)
	{
		Map<Vertex<T>,Vertex<T>> parents = new HashMap<>();
		Map<Vertex<T>, Integer> shortestWeightForVertex = new HashMap<>();
		Map<Long, Integer> unvisited = new HashMap<>();
		
		//presently populate visited with all vertices with INF weight
		for(Vertex<T> v: graph.getAllVertexes())
			unvisited.put(v.getId(), INF);
		
		//set the start node to 0 and its parent as null
		unvisited.put(start.getId(), 0);
		parents.put(start, null);
		
		while(!unvisited.isEmpty())
		{
			Entry<Long, Integer> minEntry = Collections.min(unvisited.entrySet(),weightComparator);
			Vertex<T> minVertex = graph.getVertex(minEntry.getKey());
			int U = minEntry.getValue();
			
			//remove the node from unvisited and add it to shortestPath with the weight
			unvisited.remove(minVertex.getId());			
			shortestWeightForVertex.put(minVertex, U);
			
			for(Edge<T> e: minVertex.getEdges())
			{
				Vertex<T> adj = (minVertex.equals(e.getVertex1())?e.getVertex2():e.getVertex1());
				
				// first check if the adjacent node is unvisited
				if(!unvisited.containsKey(adj.getId()))
					continue;
				
				//now check if the total weight from start is less that the current weight
				int V = unvisited.get(adj.getId());
				int UV = e.getWeight(); // first initialise to edge weight between current and adjacent node
				if(U+UV<V)
				{
					unvisited.put(adj.getId(), U+UV);
					parents.put(adj, minVertex);
				}
			}
		}
		
		Map<List<Vertex<T>>, Integer> result = formShortestPaths(parents, shortestWeightForVertex);
		return result;
	}
	
	
	public static <T> Map<List<Vertex<T>>, Integer> formShortestPaths(Map<Vertex<T>, Vertex<T>> parents,
			Map<Vertex<T>, Integer> shortestWeightForVertex) {
		Map<List<Vertex<T>>, Integer> result = new HashMap<>();
		for(Entry<Vertex<T>,Integer> e: shortestWeightForVertex.entrySet())
		{
			int minWeight = e.getValue();
			List<Vertex<T>> s = new ArrayList<>();
			s.add(e.getKey());
			for(Vertex<T> parent = parents.get(e.getKey());parent!=null;)
			{
				s.add(parent);
				parent= parents.get(parent);
			}
			Collections.reverse(s);
			System.out.println("Path = "+s+" , minWeight = "+minWeight);
			result.put(s, minWeight);
		}
		return result;
	}
	
	public static void main(String[] args) {
		Graph<String> graph;
		graph = new Graph<>(false);
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
		
		graph.addEdge(A, B, 5);
		graph.addEdge(A, D, 9);
		graph.addEdge(A, E, 2);
		graph.addEdge(B, C, 2);
		graph.addEdge(E, F, 3);
		graph.addEdge(C, D, 3);
		graph.addEdge(F, D, 2);
		
		getShortestPaths(graph, graph.getVertex(1));
	}
	
}
