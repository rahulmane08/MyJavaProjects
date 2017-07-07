package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import list.LinkedList;
import list.ListNode;

public class Graph<T>
{	
	
	private final Map<ListNode<T>, LinkedList<T>> map;
	private final int count;
	private final boolean directed;
	public Graph(T[] vertices, boolean directed)
	{
		count=vertices.length;
		map = new HashMap<>();
		for(int i=0;i<count;i++)
			map.put(new ListNode<>(vertices[i]), new LinkedList<>());
		this.directed=directed;
	}
	
	public void addEdge(T source , T dest)
	{
		//undirected graph, add from source to dest
		LinkedList<T> list = map.get(new ListNode<T>(source));
		list.insert(dest);
		
		//also add edge from dest to source
		if(!directed)
		{
			list = map.get(new ListNode<T>(dest));
			list.insert(source);
		}
		
	}
	
	public LinkedList<T> getConnectedNodes(ListNode<T> data)
	{
		return map.get(data);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Entry<ListNode<T>, LinkedList<T>> entry: map.entrySet())
			s += "Vertex:"+entry.getKey()+", connects to "+entry.getValue()+"\n";
		return s;
	}
	
	public ListNode<T>[] getVertices()
	{
		ListNode<T>[] arr = new ListNode[map.size()];
		int index = 0;
		for(ListNode<T> vertex: map.keySet())
		{
			arr[index++] = vertex;
		}
		return arr;
	}
}
