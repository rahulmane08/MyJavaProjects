package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree 
{
	Node root;
	
	public BinaryTree() {
		super();
	}

	
	
	public void insert(int data)
	{
		Node newNode = new Node(null, null, data);
		if(root==null)
		{
			root = newNode;
			return;
		}
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			
			if(curr.left!=null)
			{
				queue.offer(curr.left);
			}
			else
			{
				curr.left = newNode;
				return;
			}
				
			if(curr.right!=null)
			{
				queue.offer(curr.right);
			}
			else
			{
				curr.right = newNode;
				return;
			}
		}
	}
	
	public void delete(int data)
	{
		if(root==null)
			return;
		Node nodeToDelete = null, deepestNode = null;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty())
		{
			deepestNode = queue.poll();
			if(nodeToDelete==null && deepestNode.data==data)
				nodeToDelete = deepestNode;
			if(deepestNode.left!=null)
				queue.offer(deepestNode.left);
			if(deepestNode.right!=null)
				queue.offer(deepestNode.right);
		}
		if(nodeToDelete==null)
			return; //the node with the data to delete is not found in tree
		System.out.println("node to delete = "+nodeToDelete);
		System.out.println("deepest node = "+deepestNode);
		nodeToDelete.data = deepestNode.data;
		deepestNode = null;
	}
	
}
