package graph.cycledetection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import graph.Graph;
import graph.Vertex;

/**
 * Hamiltonian Cycle in a graoh a cycle from first vertex to last vertex such that every node is visited only once.
 * (0)--(1)--(2)
	 |   / \   |
	 |  /   \  | 
	 | /     \ |
	(3)-------(4)
 * hamiltonian cycle: {0, 3, 4, 2, 1, 0} 
 * 
 * (0)--(1)--(2)
	 |   / \   |
	 |  /   \  | 
	 | /     \ |
	(3)      (4)
	no hamiltonian cycle as 1 is visited twice
 * @author rahul
 *
 */
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
				
				if(!visited.contains(adjVertex.getId()))
					return hasHamiltonianCycle(start, adjVertex, totalVertexes, visited, result);
					
			}
		visited.remove(curr.getId());
		result.remove(curr);
		return false;
	}
	
}
