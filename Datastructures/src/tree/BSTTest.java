package tree;

public class BSTTest {
	public static void main(String[] args) {
		BinarySearchTree bst = createbst();
		Node root = bst.root;
//		TreeUtils.MorrisTraversals.inOrderTraversal(root);
		TreeUtils.MorrisTraversals.preOrderTraversal(root);
		/*bst.delete(20);
		TreeUtils.Traversals.inOrderIterative(root);
		
		BinarySearchTree bstX = new BinarySearchTree();
		bstX.insert1(bstX.root, 10);
		bstX.insert1(bstX.root, 5);		
		bstX.insert1(bstX.root, 3);
		bstX.insert1(bstX.root, 4);
		bstX.insert1(bstX.root, 6);
		bstX.insert1(bstX.root, 7);
		bstX.insert1(bstX.root, 8);
		TreeUtils.Traversals.inOrderIterative(bstX.root);
		
		System.out.println("Print range between 4 and 7");
		BSTUtils.printRange(bstX.root, 4, 11);
		
		
		System.out.println("LCA(1,20) : "+ BSTUtils.lca(bst.root, 1, 20));
		System.out.println("LCA(1,5) : "+ BSTUtils.lca(bst.root, 1, 5));
		System.out.println(BSTUtils.isBST(root));
		
		
		BinaryTree bt = new BinaryTree();
		bt.insert(3);
		bt.insert(2);
		bt.insert(5);
		bt.insert(1);
		bt.insert(4);
		
		System.out.println(BSTUtils.isBST(bt.root));
		System.out.println(BSTUtils.isBST1(bt.root));
		
		
		System.out.println(BSTUtils.KthSmallest1.kthSmallest(bst.root, 10));
		
		
		 
		//convert to sorted DLL
		System.out.println("Converting to DLL");
		Node head = BSTUtils.convertToSortedDLL(bst.root);
		while(head!=null)
		{
			System.out.println(head+", left => "+head.left+", right => "+head.right);
			head = head.right;
		}
		
		//convert to sorted CLL
		bst = createbst();
		System.out.println("Converting to CLL");
		head = BSTUtils.convertToSortedCLL(bst.root);
		Node temp = head;
		do
		{
			System.out.println(head+", left => "+head.left+", right => "+head.right);
			head = head.right;
		}
		while(temp!=head);
		
		BinaryTree btFromArray = new BinaryTree(new int[]{1,2,3,4,5,6,7});
		TreeUtils.Traversals.inOrderTraversal(btFromArray.root);
		
		bt.root = BSTUtils.createBalancedBST(new int[]{2,3,4,5,6,7,8}); 
		System.out.println(TreeUtils.sum(bt.root));
		BSTUtils.TreeNodesWithSumOfGreaterNodes.convertToTreeWithMaxNodeSum(bt.root);
		TreeUtils.Traversals.inOrderTraversal(bt.root);
		
		bstX = new BinarySearchTree();
		bstX.insert(bstX.root,4);
		bstX.insert(bstX.root,3);
		bstX.insert(bstX.root,2);
		bstX.insert(bstX.root,1);		
		System.out.println("All internal nodes have 1 child = "+BSTUtils.hasOneChildForEachInternalNode(bstX));
		bstX.insert(bstX.root,6);
		bstX.insert(bstX.root,5);
		bstX.insert(bstX.root,7);
		System.out.println("All internal nodes have 1 child = "+BSTUtils.hasOneChildForEachInternalNode(bstX));
*/
	}

	private static BinarySearchTree createbst() {
		BinarySearchTree bst = new BinarySearchTree();
		
		bst.insert(bst.root, 10);
		bst.insert(bst.root, 5);		
		bst.insert(bst.root, 3);
		bst.insert(bst.root, 6);
		bst.insert(bst.root, 12);
		bst.insert(bst.root, 11);
		bst.insert(bst.root, 13);
		
		return bst;
	}
}
