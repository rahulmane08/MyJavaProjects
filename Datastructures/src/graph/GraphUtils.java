package graph;

public class GraphUtils 
{
	static public <T> Graph<T> transpose(Graph<T> graph)
	{
		if(graph==null)
			return null;
		if(!graph.isDirected)
			return graph;
		Graph<T> transpose = new Graph<>(graph.isDirected);
		for(Edge<T> edge:graph.getAllEdges())
			graph.addEdge(edge.getVertex2().getId(), edge.getVertex1().getId(), edge.getWeight());
		return transpose;				
	}	
	static private <T> boolean areConnected(Vertex<T> v1, Vertex<T> v2, Graph<T> graph,HashSet<Long> visited)
	{
		if(graph!=null)
		{			
			for(Vertex<T> v: v1.getAdjacentVertexes())
				if(!visited.contains(v.getId()))
				{
					visited.add(v.getId());
					if(v.equals(v2))
						return true;
					else
						return areConnected(v, v2, graph,visited);
				}
					
		}
		
		return false;		
	}
	/**
	 * 1. check if no cycles
	 * 2. start from vertex and check if all vertices are traced.
	 * @param graph
	 * @return
	 */
	static public <T> boolean checkIfGraphIsTree(Graph<T> graph)
	{
		if(graph!=null)
		{
			boolean cyclePresent = CycleDetection.detectCycle(graph);
			if(cyclePresent)
				return false;
			HashSet<Long> visited = new HashSet<>();
			Vertex<T> start = graph.getAllVertexes().iterator().next();
			GraphTraversal.dfsUtil(start, visited);
			return visited.size()==graph.getAllVertexes().size();
			
		}
		return false;
	}
}
