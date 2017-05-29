package tree;

import java.util.Stack;

public class BSTUtils 
{
	public static Node lca(Node root, int elem1, int elem2)
	{
		if(root==null)
			return null;
		if(root.data==elem1 || root.data==elem2)
			return root;
		if(elem1<root.data && elem2<root.data)
			return lca(root.left,elem1,elem2);
		if(elem1>root.data && elem2>root.data)
			return lca(root.right,elem1,elem2);
		return root;
	}
	/**
	 * this is a wrong algo
	 * @param root
	 * @return
	 */
	public static boolean isBST(Node root)
	{
		if(root==null)
			return true;
		if(root.left!=null && root.left.data>root.data)
			return false;
		if(root.right!=null && root.right.data<root.data)
			return false;
		return ((isBST(root.left)) && isBST(root.right));
	}
	public static boolean isBST1(Node root)
	{
		return isBSTUtils(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean isBSTUtils(Node root, int min,int max)
	{
		if(root==null)
			return true;
		if(root.data<min || root.data>max)
			return false;
		return (isBSTUtils(root.left, min, root.data) && (isBSTUtils(root.right, root.data, max)));
	}
	
	public static Node min(Node root)
	{
		if(root==null)
			return null;
		while(root.left!=null)
			root=root.left;
		return root;
	}
	
	public static Node max(Node root)
	{
		if(root==null)
			return null;
		while(root.right!=null)
			root=root.right;
		return root;
	}
	
	public static void printInorderPredecessorSuccessor(Node root, int data)
	{
		if(root==null)
			return;
		Node predecessor = null, successor = null;
	}
	
	
	
	public static void predecessorSuccessor(Node root, int data, Node predecessor, Node successor)
	{
		if(root==null)
			return;
		if(root.data == data)
		{
			predecessor = max(root.left);
			successor = min(root.right);
			return;
		}
		if(data<root.data)
		{
			successor = root;
			predecessorSuccessor(root.left, data, predecessor, successor);
		}
			
		if(data>root.data)
		{
			predecessor = root;
			predecessorSuccessor(root.right, data, predecessor, successor);
		}
			
	}
	
	static public int kthSmallest(Node root , int k)
	{
		if(root==null || TreeUtils.size(root)<k)
			throw new RuntimeException("root is null or k is greater than size");
		int current = 0;
		Stack<Node> s = new Stack<>();
		while(true)
		{
			while(root!=null)
			{
				s.push(root);
				root = root.left;
			}
			
			root = s.pop();
			if(++current==k)
				return root.data;
			root = root.right;
		}
	}
	
	static public Node createBalancedBST(int[] sortedArr, int left , int right)
	{
		if(left>right)
			return null;
		int mid = (left + right)/2;
		Node root = new Node(sortedArr[mid]);
		root.left = createBalancedBST(sortedArr, left, mid-1);
		root.right = createBalancedBST(sortedArr, mid+1, right);
		return root;
	}
	
	static public class KthSmallest
	{
		private static int current = 0;
		private static Node kthNode= null;
		public static int kthSmallest(Node root, int k)
		{
			find(root, k);
			current = 0;
			return kthNode.data;
		}
		private static void find(Node root, int k)
		{
			if(root==null)
				return;
			find(root.left,k);
			if(++current==k)
				kthNode = root;
			find(root.right,k);
		}
	}
	
	static public Node convertToSortedDLL(Node root)
	{
		if(root==null)
			return null;
		
		Node prev = null, head = null;
		Stack<Node> s = new Stack<>();
		while(true)
		{
			if(root!=null)
			{
				s.push(root);
				root = root.left;
			}
			else
			{
				if(s.isEmpty())
					return head;
				
				root = s.pop();
				if(head==null)
					head = root;
				
				root.left = prev;
				if(prev!=null) prev.right = root;
				prev = root;
				root = root.right;
			}	
			
		}
	}
	
	static public Node convertToSortedCLL(Node root)
	{
		if(root==null)
			return null;
		
		Node prev = null, head = null;
		Stack<Node> s = new Stack<>();
		while(true)
		{
			if(root!=null)
			{
				s.push(root);
				root = root.left;
			}
			else
			{
				if(s.isEmpty())
				{
					head.left = prev;
					prev.right = head;
					return head;
				}
					
				
				root = s.pop();
				if(head==null)
					head = root;
				
				root.left = prev;
				if(prev!=null) prev.right = root;
				prev = root;
				root = root.right;
			}	
			
		}
	}
	
}
