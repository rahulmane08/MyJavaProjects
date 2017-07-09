package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HamiltonianCycleDetector 
{
	static public <T> void printHamiltonianCycle(Graph<T> graph)
	{
		if(graph==null)
			return;
		
		Vertex<T> startVertex = graph.getAllVertexes().iterator().next();
		int totalVertexes = graph.getAllVertexes().size();
		HashSet<Long> visited = new HashSet<>();
		List<Vertex<T>> result = new ArrayList<>();
		if(hasHamiltonianCycle(startVertex, startVertex, totalVertexes, visited, result))
		{
			System.out.println("Hamiltonian cycle = "+result);
		}
		else
			System.out.println("No hamiltonian cycle");
		
	}
	static private <T> boolean hasHamiltonianCycle(Vertex<T> start, Vertex<T> curr, int totalVertexes, HashSet<Long> visited, List<Vertex<T>> result)
	{
		visited.add(curr.getId());
		result.add(curr);		
		
		List<Vertex<T>> adjacentVertexes = curr.getAdjacentVertexes();
		if(adjacentVertexes!=null && adjacentVertexes.size()!=0)
			for(Vertex<T> adjVertex: adjacentVertexes)
			{
				if(start.equals(adjVertex) && totalVertexes==result.size())
					return true;
				
				if(!visited.contains(adjVertex.id) && hasHamiltonianCycle(start, adjVertex, totalVertexes, visited, result))
					return true;
					
			}
		visited.remove(curr.getId());
		result.remove(curr);
		return false;
	}
	
}
