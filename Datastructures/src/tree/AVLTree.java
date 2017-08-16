package tree;

public class AVLTree 
{
	private Node root;
	private Node rotateRight(Node root)
	{
		Node newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;
		root.height = TreeUtils.heightRecursive(root);
		newRoot.height = TreeUtils.heightRecursive(newRoot);
		return newRoot;
	}
	
	private Node rotateLeft(Node root)
	{
		Node newRoot = root.right;
		root.left = newRoot.left;
		newRoot.left = root;
		root.height = TreeUtils.heightRecursive(root);
		newRoot.height = TreeUtils.heightRecursive(newRoot);
		return newRoot;
	}
	
	private int getBalance(Node root)
	{
		if(root==null)
			return 0;
		return root.left.height - root.right.height; 
	}	
	
	public Node insert(Node node, int data) 
	{		
		Node newNode = new Node(null, null, data);
 		if(root==null)
		{
 			root = newNode;
 			System.out.println("root node created"); 			
		}	
		
		if(node.data==data)
		{
			System.out.println(data+" already present");			
		}			
		if(data<node.data)
		{
			if(node.left==null)
				node.left=newNode;
			else
				return insert(node.left,data);
		}		
		if(data>node.data)
		{
			if(node.right==null)
				node.right=newNode;
			else
				 return insert(node.right,data);
		}
		
		//update the height of ancestor
		node.height = TreeUtils.heightRecursive(node);
		
		int balance = getBalance(node);
		if(balance>1 && data<node.left.data) //LEFT - LEFT
			return rotateRight(node);
		
		if(balance>1 && data>node.left.data) //LEFT - RIGHT
		{
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
			
		if(balance>1 && data<node.right.data) //RIGHT - RIGHT
			return rotateLeft(node);
		
		if(balance>1 && data>node.right.data) //RIGHT - LEFT
		{
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}
}
