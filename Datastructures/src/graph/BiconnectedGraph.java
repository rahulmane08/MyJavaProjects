package graph;

import java.util.Set;

/**
 * A connected graph is Biconnected if it is connected and doesn’t have any Articulation Point. We mainly need to check two things in a graph.
	1) The graph is connected.
	2) There is not articulation point in graph.
 * @author rahul
 *
 */
public class BiconnectedGraph {
	static public <T> boolean checkIfGraphIsBiconnected(Graph<T> graph)
	{
		if(graph==null)
			return false;
		boolean checkIfConnected = GraphUtils.checkIfGraphIsConnected(graph);
		if(!checkIfConnected)
			return false;
		
		Set<Vertex<T>> articulationPoints = new TarjanArticulationPoint().getArticulationPoints(graph);
		return articulationPoints.size()==0;
		
	}
}
