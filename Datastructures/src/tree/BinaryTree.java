package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree 
{
	Node root;
	
	public BinaryTree() {
		super();
	}

	public BinaryTree(int [] sortedArray)
	{
		root = BSTUtils.createBalancedBST(sortedArray, 0, sortedArray.length-1);
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
	public static boolean printAncestorsOfGivenNode(Node root)
	{
		if(root==null)
			return false;
		if(printAncestorsOfGivenNode(root.left) || printAncestorsOfGivenNode(root.right))
		{
			System.out.println(root.data);
			return true;
		}
		return false;
	}
		
	/**
	*  	 26
	        /   \
	      10     3
	    /    \     \
	  4      6      3
	 * @param root
	 * @return
	 */
	public static boolean checkIfSumTree(Node root)
	{
		if(root==null || isLeaf(root))
			return true;
		int left = sum(root.left);
		int right = sum(root.right);
		return (root.data == left+right) && checkIfSumTree(root.left) && checkIfSumTree(root.right);
	}
	
	public static boolean checkIfSubtree(Node mainRoot, Node subRoot)
	{
		if(mainRoot==null && subRoot==null)
			return true;
		if(mainRoot==null)
			return false;
		if(subRoot==null)
			return false;
		if(mainRoot.data==subRoot.data)
			 return areTreesIdentical(mainRoot, subRoot);
		return checkIfSubtree(mainRoot.left, subRoot) || checkIfSubtree(mainRoot.right, subRoot); 			
	}

	public static void convertToSumTree(Node root)
	{
		if(root==null)
			return;
		int left = sum(root.left);
		int right = sum(root.right);
		root.data = left + right;
		convertToSumTree(root.left);
		convertToSumTree(root.right);
	}
}
