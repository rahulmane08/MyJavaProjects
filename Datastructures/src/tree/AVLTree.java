package tree;

public class AVLTree
{
	public Node root;

	public AVLTree() {
		super();
	}
	
	public int height(Node node)
	{
		if(node==null)
			return 0;
		return 1+Math.max(height(node.left),height(node.right));
	}
	
	public int balance(Node node)
	{
		return height(node.left) - height(node.right);
	}
	
	
	
	public Node insert(Node node, int data)
	{
		/** 1. regular binary tree insert**/
		if(root==null)
		{
			root = new Node(data);
			return root;
		}
		if(data<node.data)
			node.left = insert(node.left,data);
		else if(data>node.data)
			node.right = insert(node.right,data);
		else
			return node;
		
		/** 2. update the height and compute the balance**/
		node.height = height(node);
		int balance = this.balance(node);
		
		/** 3.  **/
		
		
		return node;
	}
}
