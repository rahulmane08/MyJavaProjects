package tree;

import tree.TreeUtils.Traversals;

public class Test {
	public static void main(String[] args) 
	{
		/*BinarySearchTree bst = new BinarySearchTree();
		bst.insert(bst.root, 10);
		System.out.println(bst.root);
		bst.insert(bst.root, 20);
		bst.insert(bst.root, 5);		
		bst.insert(bst.root, 3);
		bst.insert(bst.root, 4);
		bst.insert(bst.root, 6);
		bst.insert(bst.root, 7);
		bst.insert(bst.root, 8);
		bst.insert(bst.root, 9);
		bst.insert(bst.root, 1);
		bst.insert(bst.root, 2);
//		System.out.println("min = "+bst.min(bst.root));
//		System.out.println("max = "+bst.max(bst.root));
//		System.out.println("is 5 present = "+bst.search(bst.root, 5)+", is 99 present = "+bst.search(bst.root, 99));
//		
//		TreeUtils.preOrderIterative(tree.root);
//		TreeUtils.inOrderIterative(tree.root);
//		TreeUtils.Traversals.postOrderIterative(tree.root);
//		TreeUtils.Traversals.levelOrderTraversal(tree.root);
//		System.out.println(TreeUtils.findMax(tree.root));
//		System.out.println(TreeUtils.exists(239, tree.root));
		
		BinaryTree bt = new BinaryTree();
		bt.insert(1);		
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);
		bt.insert(6);
		bt.insert(7);
		bt.insert(8);
		
		BinaryTree bt1 = new BinaryTree();
		bt1.insert(1);		
		bt1.insert(2);
		bt1.insert(3);
		bt1.insert(4);
		bt1.insert(5);
		bt1.insert(6);
		bt1.insert(7);
		
		System.out.println("max = "+TreeUtils.findMax(bt.root));
		System.out.println("size = "+TreeUtils.size(bt.root));
		TreeUtils.Traversals.preOrderIterative(bt.root);
		TreeUtils.Traversals.levelOrderTraversal(bt.root);
		TreeUtils.Traversals.levelOrderSpiralTraversal(bt.root,true);
		System.out.println("height recursive = "+TreeUtils.heightRecursive(bt.root));
		System.out.println("height iterative = "+TreeUtils.heightInterative(bt.root));
		TreeUtils.deleteTree(bt.root);
		TreeUtils.Traversals.levelOrderTraversal(bt.root);
		System.out.println("Total leaf nodes = "+TreeUtils.countLeafNodes(bt.root));
		System.out.println("identical test = " + TreeUtils.areTreesIdentical(bt.root, bt1.root));
		
		System.out.println("all ancestor nodes => ");
		TreeUtils.printAllAncestors(bt.root);
		System.out.println("all leaf nodes => ");
		TreeUtils.printAllLeaves(bt.root);
		
		TreeUtils.depthOfEachNode(bst.root);
		System.out.println("diameter = "+TreeUtils.diameter(bst.root));
		
		System.out.println("BST level with max sum = "+TreeUtils.findLevelWithMaxSum(bst.root));
		System.out.println("BT level with max sum = "+TreeUtils.findLevelWithMaxSum(bt.root));
		
		TreeUtils.printAllRootToLeafPaths(bt.root,new Stack<>());
		TreeUtils.printPathMatchingSum(bt.root,new Stack<>(),15);
		
		System.out.println("Tree sum = "+TreeUtils.sum(bt.root));
		TreeUtils.image(bt.root);
		TreeUtils.Traversals.levelOrderTraversal(bt.root);
		
		BinaryTree bt1Mirror = new BinaryTree();
		bt1Mirror.insert(1);		
		bt1Mirror.insert(3);
		bt1Mirror.insert(2);
		bt1Mirror.insert(7);
		bt1Mirror.insert(6);
		bt1Mirror.insert(5);
		bt1Mirror.insert(4);
		
		System.out.println("mirror test = " + TreeUtils.areTreesMirrors(bt1.root, bt1Mirror.root));
		
		System.out.println("LCA of 4,5 : " + TreeUtils.lca(bt.root, 4, 5));
		System.out.println("LCA of 4,7 : " + TreeUtils.lca(bt.root, 4, 7));
		
		//create tree using inorder and preorder
		Integer[] inorder = new Integer[]{4,2,5,1,6,3};
		Integer[] preorder = new Integer[]{1,2,4,5,3,6};
		Node newTree = TreeUtils.createTreeUsingPreAndInorderSequences(inorder, preorder, 0, preorder.length-1);
		TreeUtils.Traversals.levelOrderTraversal(newTree);
		
		bt = new BinaryTree();
		bt.insert(1);		
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);
		bt.insert(6);
		bt.insert(7);
		bt.insert(8);
		
		TreeUtils.fillNextSiblings(bt.root);
		TreeUtils.Traversals.levelOrderTraversal(bt.root);
		TreeUtils.printVerticalSum(bt.root);*/
		
		BinaryTree bt = new BinaryTree();
		bt.insert(50);		
		bt.insert(8);
		bt.insert(2);
		bt.insert(3);
		bt.insert(5);
		bt.insert(1);
		bt.insert(30);

		TreeUtils.convertToSumTree(bt.root);
		Traversals.levelOrderTraversal(bt.root);
		
	}
}
