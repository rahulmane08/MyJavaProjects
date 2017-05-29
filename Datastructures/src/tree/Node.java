package tree;
public class Node {
	int height;
	Node left;
	Node right;
	Node nextSibling;
	int data;
	
	public Node(int data) {
		super();
		this.data = data;
	}
	public Node(Node left, Node right, int data) {
		super();
		this.left = left;
		this.right = right;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", nextSibling=" + (nextSibling!=null?nextSibling.data:null) + "]";
	}	
}
