package graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> 
{
	static public class Node<T> 
	{
		T data;
		int rank;
		Node parent;
		public Node(T data) {
			super();
			this.data = data;
		}
		
	}
	
	Map<T, Node<T>> map = new HashMap<>();
	
	public void makeSet(T data)
	{
		Node<T> node = new Node<>(data);
		node.rank=0;
		node.parent=node;
		map.put(data, node);
	}
	
	
	public Node<T> findSet(T data)
	{
		return findSet(map.get(data));
	}
	private Node<T> findSet(Node<T> node)
	{
		if(node==null) 
			return null;
		if(node.parent==node)
			return node;
		node.parent = findSet(node.parent);
		return node.parent;
	}
	
	public boolean union(T data1, T data2)
	{
		Node<T> parent1 = findSet(data1);
		Node<T> parent2 = findSet(data2);
		
		if(parent1.data==parent2.data)
			return false;
		
		if(parent1.rank>=parent2.rank)
		{
			parent1.rank=(parent1.rank>parent2.rank)?parent1.rank+1:parent2.rank;
			parent2.parent=parent1;
		}
		else
			parent1.parent=parent2;
		return true;
	}
}
