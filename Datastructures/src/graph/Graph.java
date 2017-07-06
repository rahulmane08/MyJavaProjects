package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import list.LinkedList;

public class Graph<T>
{	
	
	private final Map<T, LinkedList<T>> map;
	private final int count;
	public Graph(T[] vertices)
	{
		count=vertices.length;
		map = new HashMap<>();
		for(int i=0;i<count;i++)
			map.put(vertices[i], new LinkedList<>());
	}
	
	public void addEdge(T source , T dest)
	{
		//undirected graph, add from source to dest
		LinkedList<T> list = map.get(source);
		list.insert(dest);
		
		//also add edge from dest to source
		list = map.get(dest);
		list.insert(source);
	}	
	
	@Override
	public String toString() {
		String s = "";
		for(Entry<T, LinkedList<T>> entry: map.entrySet())
			s += "Vertex:"+entry.getKey()+", connects to "+entry.getValue()+"\n";
		return s;
	}
}
