package tree;

import java.util.Arrays;
import java.util.Stack;

import com.sun.org.apache.bcel.internal.generic.ISHL;

import tree.TreeUtils.OrderedArrays;

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
	
	/** 
	 * do inorder traversal, when stack starts unwinding, check if k = ++currVisit, if yes then current root is kth smallest
	 * else continue with recursion
	 * @author rahul
	 *
	 */
	
	static class KthSmallest
	{
		private static int currVisit = 0;
		
		private static KthSmallest INSTANCE = new KthSmallest();
		
		private KthSmallest(){}
		
		public static KthSmallest getInstance()
		{
			currVisit=0;
			return INSTANCE;
		}
		public Node kthSmallest(Node root, int k)
		{
			if(root==null)
				return null;
			Node left = kthSmallest(root.left, k);
			if(left!=null)
				return left;
			if(k==++currVisit)
				return root;		
			return kthSmallest(root.right, k);
		}
	}
	
	
	static class KthLargest
	{
		private static int currVisit = 0;
		
		private static KthLargest INSTANCE = new KthLargest();
		
		private KthLargest(){}
		
		public static KthLargest getInstance()
		{
			currVisit=0;
			return INSTANCE;
		}
		
		public Node kthLargest(Node root, int k)
		{
			if(root==null)
				return null;
			Node right = kthLargest(root.right, k);
			if(right!=null)
				return right;
			if(k==++currVisit)
				return root;		
			return kthLargest(root.left, k);
		}
	}
	static public Node createBalancedBST(int[] sortedArr)
	{
		return createBalancedBST(sortedArr,0,sortedArr.length-1);
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
	
	static public class KthSmallest1
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
	
	static public void printRange(Node root, int k1, int k2)
	{
		if(root==null)
			return;
		printRange(root.left, k1,k2);
		if(k1<root.data && root.data<=k2)
			System.out.println(root.data);
		printRange(root.right, k1, k2);
	}
	
	/**
	 * 
	 *   	  50
           /      \
         30        70
        /   \      /  \
      20    40    60   80 

		The above tree should be modified to following 

              260
           /      \
         330        150
        /   \       /  \
      350   300    210   80
      
      
	 * @author rahul
	 *	1. First find the tree sum
	 *	2. then inorder traversal, from the left most node start setting data = treeSum and follow the recursion with treesum-data
	 */
	static class TreeNodesWithSumOfGreaterNodes
	{
		static int treeSum;
		static public void convertToTreeWithMaxNodeSum(Node root)
		{
			if(root==null)
				return;
			treeSum = TreeUtils.sum(root);
			updateTreeSumOnNodes(root);
		}
		static private void updateTreeSumOnNodes(Node root)
		{
			if(root==null)
				return;
			updateTreeSumOnNodes(root.left);
			int nextSum = treeSum - root.data;
			root.data = treeSum;
			treeSum = nextSum;
			updateTreeSumOnNodes(root.right);
			
		}
	}
	
	static public int countDirectChildren(Node root)
	{
		if(root==null)
			return 0;
		return (root.right!=null?1:0)+(root.left!=null?1:0);
	}
	
	static public boolean hasOneChildForEachInternalNode(BinarySearchTree tree)
	{
		if(tree==null || tree.root == null)
			return false;
		Node root = tree.root;
		if(TreeUtils.isLeaf(root))
			return false;
		return hasOneChildForEachInternalNode(root.left) 
				&& hasOneChildForEachInternalNode(root.right);
	}
	static public boolean hasOneChildForEachInternalNode(Node root)
	{
		if(root==null || TreeUtils.isLeaf(root))
			return true;
		/*boolean yes = countDirectChildren(root)==1;
		if(yes)
		{
			if(root.left!=null)
				yes = hasOneChildForEachInternalNode(root.left);
			if(yes && root.right!=null)
				yes = hasOneChildForEachInternalNode(root.right);
		}
		return yes;*/
		return (countDirectChildren(root)==1) 
				&& (hasOneChildForEachInternalNode(root.left)) 
				&& (hasOneChildForEachInternalNode(root.right));
	}
	public static int ceil(Node root, int data)
	{
		if(root==null)
			return -1;
		if(root.data==data)
			return data;
		if(root.data<data)
			return ceil(root.right,data);
		int ceil = ceil(root.left,data);
		return (ceil>=data?ceil:root.data);
	}
	public static int floor(Node root, int data)
	{
		if(root==null)
			return -1;
		if(root.data==data)
			return data;
		if(root.data<data)
			return floor(root.left,data);
		int floor = floor(root.right,data);
		return (floor<=data?floor:root.data);
	}
	public static boolean checkIfPreorderIsBST(int[] preorder)
	{		
		Stack<Integer> s = new Stack<Integer>();
		int root = Integer.MIN_VALUE;
		for(int i=0;i<preorder.length;i++)
		{
			if(preorder[i]<root)
				return false;
			while(!s.isEmpty() && preorder[i]>s.peek())
				root = s.pop();			
			s.push(preorder[i]);
		}
		
		return true;
	}
	
	/**
	 * 
	 * convert to DLL and use start pair finding algo using 2 pointers
	 * @param root
	 * @param n
	 */
	public static void printNodeSumEqualsN(Node root, int n)
	{
		if(root==null)
			return;
		Node first = convertToSortedDLL(root);
		Node last = first;
		while(last.right!=null)
			last = last.right;
		while(first!=last)
		{
			if(first.data+last.data==n)
			{
				System.out.println("Pair found ("+first.data+","+last.data+")");
				first = first.right;
				if(first==last)
					break;
				last = last.left;
			}
			else if(first.data+last.data>n)
				last = last.left;
			else
				first = first.right;
		}
	}
	
	/**
	 * get inorder[] from the BT
	 * sort the inorder[]
	 * fill the BT in inorder using the elements from inorder[] 
	 * 
	 * @param root
	 */
	static public void toBSTofSameStructure(Node root)
	{
		if(root==null)
			return;
		int[] inorderArr = OrderedArrays.getInstance().toInorderArray(root);
		Arrays.sort(inorderArr);
		OrderedArrays.getInstance().fillTreeWithInorderArr(root, inorderArr);
	}
	
	
	public static void printCommonNodes(Node root1, Node root2)
	{
		if(root1==null || root2 ==null)
			return;
		Stack<Node> s1 = new Stack<>();
		Stack<Node> s2 = new Stack<>();
		while(true)
		{
			if(root1!=null)
			{
				s1.push(root1);
				root1=root1.left;
			}
			else if(root2!=null)
			{
				s2.push(root2);
				root2=root2.left;
			}
			else 
			{
				if(s1.isEmpty() || s2.isEmpty())
					break;
				
				root1 = s1.pop();
				root2 = s2.pop();
				
				if(root1.data==root2.data)
				{
					System.out.println("Found a matching node with data = "+root1.data);
					root1 = root1.right;
					root2 = root2.right;					
				}
				else if(root1.data<root2.data)
				{
					root1=root1.right;
					s2.push(root2);
					root2=null;
				}
				else
				{
					root2=root2.right;
					s1.push(root1);
					root1=null;
				}
			}
		}
	}
	
	
	
	public BinarySearchTree merge(BinarySearchTree bst1, BinarySearchTree bst2)
	{
		if(bst1==null && bst2==null)
			return null;
		if(bst1==null)
			return bst2;
		if(bst2==null)
			return bst1;
		int[] inorder1 = OrderedArrays.getInstance().toInorderArray(bst1.root);
		int[] inorder2 = OrderedArrays.getInstance().toInorderArray(bst2.root);
		System.out.println("inorder 1 = "+inorder1);
		System.out.println("inorder 2 = "+inorder2);
		int[] merged = array.Arrays.merge(inorder1, inorder2);
		Node root = createBalancedBST(merged);
		return new BinarySearchTree(root);
	}
	
	public static Node inorderSuccessor(Node root, int data)
	{
		if(root == null)
			return null;
		Node successor = null;
		if(data<root.data)
			successor = inorderSuccessor(root.left, data);
		else if(root.data<data)
			successor = inorderSuccessor(root.right, data);
		else
			return min(root.right);
		
		if(successor==null)
			successor = root;
		
		return successor;
	}
	
	
	
	
	public static Node inorderPredecessor(Node root, int data)
	{
		if(root==null)
			return null;
		Node predecessor = null;
		if(data<root.data)
			predecessor = inorderPredecessor(root.left, data);
		else if(root.data<data)
			predecessor = inorderPredecessor(root.right, data);
		else
			return max(root.left);
		if(predecessor==null)
			predecessor=root;
		
		return predecessor;
	}
	static class InorderPredecssorSuccessor
	{
		private static Node predecessor,successor;
		private static void predecessorSuccessor(Node root, int data)
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
				predecessorSuccessor(root.left, data);
			}
				
			if(data>root.data)
			{
				predecessor = root;
				predecessorSuccessor(root.right, data);
			}				
		}
		
		public static Node inorderPredecessor(Node root, int data)
		{
			predecessorSuccessor(root, data);
			return predecessor;
		}
		public static Node inorderSuccessor(Node root, int data)
		{
			predecessorSuccessor(root, data);
			return successor;
		}
		
	}
	static public class CorrectBST
	{
		private Node prev; 
		private Node first;
		private Node mid;
		private Node second;		
		
		
		public CorrectBST() {
			super();
		}
		public  void correctBst(Node root)
		{
			correctBstUtil(root);
			if(first!=null && second!=null)
				swapData(first,second);
			else if(first!=null && mid!=null)
				swapData(first,mid);
		}
		private void swapData(Node x, Node y)
		{
			int temp = x.data;
			x.data = y.data;
			y.data = temp;
		}
		public void correctBstUtil(Node root)
		{
			
			if(root==null)
				return;
			correctBstUtil(root.left);			
			if(prev!=null && prev.data>root.data)
			{
				if(first==null)
				{
					first = prev;
					mid = root;
				}	
				else
					second=root;				
			}
			
			prev=root;
			correctBstUtil(root.right);
			
		}
	}	
}
