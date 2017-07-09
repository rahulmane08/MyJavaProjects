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
}
