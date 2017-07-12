package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * https://www.youtube.com/watch?v=2kREIkF9UAs&index=13&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j&t=483s
 */
public class TarjanArticulationPoint 
{	
	private int time=0;
	public <T> Set<Vertex<T>> getArticulationPoints(Graph<T> graph)
	{
		Set<Long> visited = new HashSet<>();
		Set<Vertex<T>> articulationPoints = new HashSet<>();
		
		Map<Vertex<T>, Integer> visitTimes = new HashMap<>();
		Map<Vertex<T>, Integer> lowTimes = new HashMap<>();
		Map<Vertex<T>, Vertex<T>> parents = new HashMap<>();
		
		Vertex<T> start = graph.getAllVertexes().iterator().next();
		this.apUtil(graph, start, visited, visitTimes, lowTimes, parents, articulationPoints);
		time=0;
		return articulationPoints;
	}
	
	private <T> void apUtil(Graph<T> graph
									,Vertex<T> vertex
									,Set<Long> visited
									,Map<Vertex<T>, Integer> visitTimes
									,Map<Vertex<T>, Integer> lowTimes
									,Map<Vertex<T>, Vertex<T>> parents
									,Set<Vertex<T>> articulationPoints)
	{
		visited.add(vertex.getId());
		visitTimes.put(vertex, time);
		lowTimes.put(vertex, time);
		time++;
		int childCount =0;
		boolean isArticulationPoint = false;
		for(Vertex<T> adj: vertex.getAdjacentVertexes())
		{
			if(adj.equals(parents.get(vertex)))
				continue;
			if(!visited.contains(adj.getId()))
			{
				parents.put(adj, vertex);				
				childCount++;
				apUtil(graph,adj, visited, visitTimes, lowTimes, parents, articulationPoints);
				
				if(visitTimes.get(vertex)<=lowTimes.get(adj))
					isArticulationPoint=true;
				else
				{
					lowTimes.put(vertex, lowTimes.get(adj));
				}
			}
			else
			{
				
				int newLowTime = Math.min(visitTimes.get(adj), time);		
				lowTimes.put(vertex, newLowTime);
			}
		}
		if((isArticulationPoint && parents.get(vertex)!=null) || (parents.get(vertex)==null && childCount>=2))
			articulationPoints.add(vertex);
	}
}
