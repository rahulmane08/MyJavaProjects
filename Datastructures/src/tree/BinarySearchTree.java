package tree;

public class BinarySearchTree {
	Node root;

	
	
	public BinarySearchTree(Node root) {
		super();
		this.root = root;
	}


	public BinarySearchTree() {
		super();
	}
	
	public BinarySearchTree(int [] sortedArray)
	{
		root = BSTUtils.createBalancedBST(sortedArray, 0, sortedArray.length-1);
	}

	public void reverse(Node node)
	{
		if(node==null)
			return;
		
		if(node.left==null && node.right==null)
			return;
		
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
		
		reverse(node.left);
		reverse(node.right);
	}
	
	public void insert(Node node,int data)
	{
		
		Node newNode = new Node(null, null, data);
 		if(root==null)
		{
 			root = newNode;
 			System.out.println("root node created");
 			return;
		}	
		
		if(node.data==data)
		{
			System.out.println(data+" already present");
			return;
		}
			
		if(data<node.data)
		{
			if(node.left==null)
				node.left=newNode;
			else
				insert(node.left,data);
		}
			
		
		if(data>node.data){
			if(node.right==null)
				node.right=newNode;
			else
				insert(node.right,data);
		}
			
	}
	
	
	public Node insert1(Node node, int data)
	{
 		if(root==null)
		{
 			root = new Node(null, null, data);
 			System.out.println("root node created");
 			return root;
		}
 		if(node==null)
 		{
 			return new Node(null, null, data);
 		}
 		
 		if(node.data == data)
 			return node;
 		
 		if(node.data>data)
 			node.left = insert1(node.left, data);
 		else if(node.data<data)
 			node.right = insert1(node.right, data);
 		
 		return node;
	}
	public Node min(Node rootNode)
	{
		Node node = rootNode;
		while(node!=null && node.left!=null)
			node=node.left;
		return node;
			
	}
	
	public Node max(Node rootNode)
	{
		Node node = rootNode;
		while(node!=null && node.right!=null)
			node=node.right;
		return node;
			
	}
	
	
	
	public Node search(Node node, int data)
	{
		if(node==null)
			return null;
		
		if(node.data==data)
			return node;
		else if(data<node.data)
			return search(node.left, data);
		else
			return search(node.right, data);
			
	}
	
	public void delete(int data)
	{
		this.delete(root,data);
	}
	private Node delete(Node root,int data)
	{
		if(root==null || root.data == data)
			return root;
		Node nodeToDelete = null;
		if(data<root.data)
		{
			nodeToDelete = delete(root.left,data);
			root.left = nodeToDelete;
		}
			
		else if(root.data<data)
		{
			nodeToDelete = delete(root.right,data);
			root.right = nodeToDelete;
		}
			
		if(nodeToDelete == null)
			return null;
		
		if(TreeUtils.isLeaf(nodeToDelete))
			nodeToDelete = null;
		else if(nodeToDelete.left!=null && nodeToDelete.right!=null)
		{
			Node successor = min(nodeToDelete.right);
			root.data = successor.data;
			return delete(nodeToDelete.right,successor.data);
		}
		else if(nodeToDelete.left!=null)
		{
			root.left = nodeToDelete.left;
			nodeToDelete.left = null;
		}
		else if(nodeToDelete.right!=null)
		{
			root.right = nodeToDelete.right;
			nodeToDelete.right = null;
		}
		
		return root;
	}
	
}
