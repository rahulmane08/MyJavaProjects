package graph.shortestpath;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BellmanFordShortestPath 
{
	private static final int INF = Integer.MAX_VALUE;
	public static <T> Map<List<Vertex<T>>, Integer> getShortestPaths(Graph<T> graph, Vertex<T> start)
	{
		//works only for directed graphs
		if(graph==null || !graph.isDirected())
			return null;
		
		Map<List<Vertex<T>>, Integer> result = new HashMap<>();
		Map<Vertex<T>, Integer> shortestWeightForVertex = new HashMap<>();
		Map<Vertex<T>, Vertex<T>> parents = new HashMap<>();

		for(Vertex<T> v: graph.getAllVertexes())
			shortestWeightForVertex.put(v,INF);
		shortestWeightForVertex.put(start, 0);
		parents.put(start, null);
		
		int totalVertexes = graph.getAllVertexes().size();
		
		for(int i=0;i<totalVertexes-1;i++)
			for(Edge<T> edge: graph.getAllEdges())
			{
				Vertex<T> u = edge.getVertex1();
				Vertex<T> v = edge.getVertex2();
				
				int U = shortestWeightForVertex.get(u);
				int V = shortestWeightForVertex.get(v);
				int UV = edge.getWeight();
				
				if(U+UV<V)
				{
					shortestWeightForVertex.put(v, U+UV);
					parents.put(v, u);
				}
			}
		
		//additional check for decreasing negative weight cycle
		for (Edge<T> edge : graph.getAllEdges()) {
            Vertex<T> u = edge.getVertex1();
            Vertex<T> v = edge.getVertex2();
            if (shortestWeightForVertex.get(u) + edge.getWeight() < shortestWeightForVertex.get(v)) {
                throw new RuntimeException("Graph has a negative weight cycle and hence solution not possible");
            }
        }
		
		for(Entry<Vertex<T>, Integer> entry: shortestWeightForVertex.entrySet())
		{
			List<Vertex<T>> path = new ArrayList<>();
			result.put(path, entry.getValue());			
			path.add(entry.getKey());
			for(Vertex<T> parent=parents.get(entry.getKey());parent!=null;)
			{
				path.add(parent);
				parent = parents.get(parent);
			}
		}
		
		
		return result;
	}
}
