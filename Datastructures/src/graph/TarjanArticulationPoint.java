package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Date 08/22/2015
 * @author Tushar Roy
 *
 * Find articulation points in connected undirected graph.
 * Articulation points are vertices such that removing any one of them disconnects the graph.
 *
 * We need to do DFS of this graph and keep visitedTime and lowTime for each vertex.
 * lowTime is keeps track of back edges.
 *
 * If any one of following condition meets then vertex is articulation point.
 *
 * 1) If vertex is root of DFS and has atlesat 2 independent children.(By independent it means they are
 * not connected to each other except via this vertex). This condition is needed because if we
 * started from corner vertex it will meet condition 2 but still is not an articulation point. To filter
 * out those vertices we need this condition.
 *
 * 2) It is not root of DFS and if visitedTime of vertex <= lowTime of any adjacent vertex then its articulation point.
 *
 * Time complexity is O(E + V)
 * Space complexity is O(V)
 *
 * References:
 * https://en.wikipedia.org/wiki/Biconnected_component
 * http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
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
	
	
	/**
	 *     A----C-------D
	 *     |   / 
	 *     |  /
	 *     | /
	 *     B
	 * 
	 * start vertex = C, so we start DFS with C, hence its parent is null. parents{C,NULL} , time = 0, lowTimes{C,0}, visitTimes{C,0}, visited(C)
	 * DFS(C):
	 * 	time=1
	 * 	move to A: , parents{A,C} , lowTimes{A,1}, visitTimes{A,1} , visited(A)
	 * 	  DFS(A):
	 * 		time=2
	 * 		move to C , but parent[A] = C, skip , childCount(A) = 1
	 * 		move to B , parents{B,A} , lowTimes{B,2}, visitTimes{B,2}, childCount(A) = 2
	 * 		DFS(B):
	 * 			move to A, but parent[B] = A, skip
	 * 			move to C, but visited(C) = true, HENCE ITS A BACKEDGE, IN THIS CASE LOWTIME OF B NEEDS TO BE VISIT TIME OF C:lowTimes{B,1}
	 * 	  check visitedTime[A] < = lowTimes[B] , 2 > 1 hence A is not a articulation point, but update lowTimes{A,1}		
	 * 	check visitedTime[C] < = lowTimes[A] , 1 <= 1 and C is also the start vertex as parent[C] = NULL, yes its a AP for A.
	 * 	move to B: B is in visited so skip.
	 * 	time=4
	 *  parents{D,C} , lowTimes{D,4}, visitTimes{D,4}, childCount(C) = 2	
	 * 	check visitedTime[C] < = lowTimes[D] , 1 <= 4 and C is also the start vertex as parent[C] = NULL, yes its a AP for D.
	 * 
	 * 
	 * 
	 * @param graph
	 * @param vertex
	 * @param visited
	 * @param visitTimes
	 * @param lowTimes
	 * @param parents
	 * @param articulationPoints
	 */
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
					lowTimes.put(vertex, lowTimes.get(adj));
				
			}
			else
			{
				//BACKEDGE CONDITION, UPDATE THE LOW TIME FROM THE BACKEDGE VERTEXES VISIT TIME
				int newLowTime = Math.min(visitTimes.get(adj), time);		
				lowTimes.put(vertex, newLowTime);
			}
		}
		if((isArticulationPoint && parents.get(vertex)!=null) || (parents.get(vertex)==null && childCount>=2))
			articulationPoints.add(vertex);
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
		graph.addEdge(B, C, 1);
		graph.addEdge(C, D, 1);
		graph.addEdge(C, A, 1);
		
		System.out.println(new TarjanArticulationPoint().getArticulationPoints(graph));
	}
}
