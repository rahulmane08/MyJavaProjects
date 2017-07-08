package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class KosarajuStronglyConnectedComponent {
	
	static public <T> void findSCC(Graph<T> graph)
	{
		if(graph==null)
			return;
		HashSet<Long> visited = new HashSet<>();
		Stack<Vertex<T>> stack = new Stack<>();
		
		//insert into the stack by the visit times. TOPOLOGICAL SORT
		for(Vertex<T> vertex:graph.getAllVertexes())
			if(!visited.contains(vertex.getId()))
				TopologicalSort.topologicalSortUtil(vertex, visited, stack);
		
		//transpose the graph
		Graph<T> graphT = GraphUtils.transpose(graph);
		
		//clear the visited to now do second pass
		visited.clear();
		
		//create a list of sets that holds the scc's
		List<Set<Vertex<T>>> result = new ArrayList<>();
		
		//pop each element from stack , the ones with highest visit times will be at the top, and then do DFS in the transpose
		while(!stack.isEmpty())
		{
			//get the vertex from the transposed graph
			Vertex<T> vertex = graphT.getVertex(stack.pop().getId());
			Set<Vertex<T>> set = new HashSet<>();
			if(!visited.contains(vertex.getId()))
				DFSUtilForTranspose(vertex, visited, set);			
			result.add(set);
		}
		
		System.out.println("SCC components: ");
		for(Set<Vertex<T>> set: result)
			System.out.println(set);
	}
	
	
	private static <T> void DFSUtilForTranspose(Vertex<T> vertex, HashSet<Long> visited, Set<Vertex<T>> set)
	{
		visited.add(vertex.getId());
		for(Vertex<T> adjVertex: vertex.getAdjacentVertexes())
			if(!visited.contains(adjVertex.getId()))
				DFSUtilForTranspose(adjVertex, visited, set);
		set.add(vertex);
	}
}
