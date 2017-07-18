package tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


import utils.Utils;


public class TreeUtils 
{
	public static class OrderedArrays
	{
		private static int index = 0;
		
		private OrderedArrays(){}
		
		public static OrderedArrays instance = new OrderedArrays();
		
		public static OrderedArrays getInstance()
		{
			index = 0;
			return instance;
		}
		
		public int[] toInorderArray(Node root)
		{
			int[] inorderArr = new int[TreeUtils.size(root)];
			fillInOrderArray(root, inorderArr);			
			return inorderArr;
		}
		public int[] toPreorderArray(Node root)
		{
			int[] preOrderArr = new int[TreeUtils.size(root)];
			fillPreOrderArray(root, preOrderArr);			
			return preOrderArr;
		}
		public int[] toPostorderArray(Node root)
		{
			int[] postOrderArr = new int[TreeUtils.size(root)];
			fillPostOrderArray(root, postOrderArr);
			return postOrderArr;
		}
		private void fillInOrderArray(Node root, int[] inorderArr)
		{
			if(root==null)
				return;
			fillInOrderArray(root.left, inorderArr);
			inorderArr[index++]=root.data;
			fillInOrderArray(root.right, inorderArr);			
		}
		
		private void fillPreOrderArray(Node root, int[] inorderArr)
		{
			if(root==null)
				return;
			inorderArr[index++]=root.data;
			fillPreOrderArray(root.left, inorderArr);			
			fillPreOrderArray(root.right, inorderArr);			
		}
		private void fillPostOrderArray(Node root, int[] inorderArr)
		{
			if(root==null)
				return;			
			fillPostOrderArray(root.left, inorderArr);			
			fillPostOrderArray(root.right, inorderArr);
			inorderArr[index++]=root.data;
		}
		
		public void fillTreeWithInorderArr(Node root, int[] inorder)
		{
			if(root==null)
				return;
			fillTreeWithInorderArr(root.left,inorder);
			root.data = inorder[index++];
			fillTreeWithInorderArr(root.right,inorder);
		}
		
		public void fillTreeWithPreOrderArr(Node root, int[] inorder)
		{
			if(root==null)
				return;
			root.data = inorder[index++];
			fillTreeWithPreOrderArr(root.left,inorder);			
			fillTreeWithPreOrderArr(root.right,inorder);
		}
		public void fillTreeWithPostOrderArr(Node root, int[] inorder)
		{
			if(root==null)
				return;
			fillTreeWithPostOrderArr(root.left,inorder);			
			fillTreeWithPostOrderArr(root.right,inorder);
			root.data = inorder[index++];
		}
		
	}
	public static class Traversals
	{
		public static void inOrderIterative(Node root)
		{
			Stack<Node> stack = new Stack<>();
			Node curr = root;
			while(curr!=null || !stack.isEmpty())
			{
				if(curr!=null)
				{
					stack.push(curr);
					curr = curr.left;
				}
				else
				{
					curr = stack.pop();
					System.out.print(curr.data + " ");
					curr = curr.right;
				}
			}
			System.out.println();
		}
		
		
		public static void preOrderIterative(Node root)
		{
			Stack<Node> stack = new Stack<>();
			Node curr = root;
			while(true)
			{
				if(curr!=null)
				{
					System.out.print(curr.data+" ");
					stack.push(curr);
					curr = curr.left;
				}
				else
				{
					if(stack.isEmpty())
						break;
					curr = stack.pop();				
					curr = curr.right;
				}
			}
			System.out.println();
		}
		
		/**
		 * use 2 pointers
		 * a. curr to iterate over left branch till leaf
		 * b. temp node that pops and prints in post order
		 * c. pop from stack top and check if theres a right node if yes assign it to current and then add its left branch in stack
		 * d. if theres not right node, pop from stack
		 * 		
		 * 
		 * @param root
		 */
		static public void postOrderIterative(Node root){
	        Node current = root;
	        Stack<Node> stack = new Stack<>();
	        while(current != null || !stack.isEmpty()){
	            if(current != null){
	                stack.push(current);
	                current = current.left;
	            }else{	                
	                if (stack.peek().right != null) { //check if right node is present , if yes then set current to it and then push its entire left branch into stack
	                	current = stack.peek().right;
	                } else {	                    
	                    Node temp = stack.pop();
	                    System.out.print(temp.data + " ");
	                    while (!stack.isEmpty() && temp == stack.peek().right) { //if you just popped a right node also pop its parent.
	                        temp = stack.pop();
	                        System.out.print(temp.data + " ");
	                    }
	                }
	            }
	        }
	        System.out.println();
	    }
		
		public static void postOrderIterative2(Node root)
		{	
			Stack<Node> tempStack = new Stack<>();
			Stack<Node> finalStack = new Stack<>();
			tempStack.push(root);
			
			while(!tempStack.isEmpty())
			{
				root = tempStack.pop();			
				finalStack.push(root);
				if(root.left!=null)
					tempStack.push(root.left);
				if(root.right!=null)
					tempStack.push(root.right);
			}
			while(!finalStack.isEmpty())
				System.out.println(finalStack.pop());
		}
		
		public static void preOrderIterative2(Node root)
		{
			Stack<Node> stack = new Stack<>();
			if(root!=null)
				stack.push(root);
			while(!stack.isEmpty())
			{
				root = stack.pop();
				System.out.println(root.data);
				if(root.right!=null)
					stack.push(root.right);
				if(root.left!=null)
					stack.push(root.left);
			}
		}
		public static void inOrderTraversal(Node node)
		{
			if(node==null)
				return;
			inOrderTraversal(node.left);
			System.out.println(node.data);
			inOrderTraversal(node.right);
		}
		
		public static void preOrderTraversal(Node node)
		{
			if(node==null)
				return;
			System.out.println(node.data);
			preOrderTraversal(node.left);		
			preOrderTraversal(node.right);
		}
		
		public static void postOrderTraversal(Node node)
		{
			if(node==null)
				return;
			postOrderTraversal(node.left);		
			postOrderTraversal(node.right);
			System.out.println(node.data);		
		}
		
		public static void levelOrderTraversal(Node root)
		{
			System.out.println("===LEVEL ORDER TRAVERSAL===");
			if(root==null)
				return;
			Queue<Node> queue = new ArrayDeque<>();
			queue.offer(root);
			while(!queue.isEmpty())
			{
				Node curr = queue.poll();
				System.out.println(curr);
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
		}
		
		/**
		 * use two stacks: currentLevel, nextLevel
		 * add root to currentLevel , add its childs to nextLevel
		 * pop and print from currentLevel and when currentLevel is empty then assign it to nextLevel and reset nextLevel
		 * @param root
		 * @param leftToRight
		 */
		public static void levelOrderSpiralTraversal(Node root, boolean leftToRight)
		{
			System.out.println("===LEVEL ORDER SPIRAL TRAVERSAL===");
			if(root==null)
				return;
			Stack<Node> currentLevel = new Stack<>();
			Stack<Node> nextLevel = new Stack<>();
			currentLevel.push(root);
			while(!currentLevel.isEmpty())
			{
				Node curr = currentLevel.pop();
				System.out.println(curr.data);
				if(leftToRight)
				{
					if(curr.left!=null)
						nextLevel.push(curr.left);
					if(curr.right!=null)
						nextLevel.push(curr.right);
				}
				else
				{
					if(curr.right!=null)
						nextLevel.push(curr.right);
					if(curr.left!=null)
						nextLevel.push(curr.left);					
				}
				if(currentLevel.isEmpty())
				{
					currentLevel = nextLevel;
					nextLevel = new Stack<>();
					leftToRight = !leftToRight;
				}
			}
		}
	}
	
	public static int findMax(Node root)
	{
		int max = Integer.MIN_VALUE;
		if(root!=null)
		{
			int curr = root.data;
			int left = findMax(root.left);
			int right = findMax(root.right);
			
			if(left>right)
				max=left;
			else
				max=right;
			if(curr>max)
				max=curr;
		}
		return max;
	}
	
	public static boolean exists(int data, Node root)
	{
		if(root==null)
			return false;
		if(root.data==data)
			return true;
		boolean exists = exists(data, root.left);
		if(exists)
			return true;
		else
			return exists(data,root.right);
	}
	
	public static int size(Node root)
	{
		if(root==null)
			return 0;
		return 1+size(root.left) + size(root.right);
	}
	
	public static void deleteTree(Node root)
	{
		if(root==null)
			return;
		deleteTree(root.left);
		deleteTree(root.right);
		
		//eliminate the references
		root.left = null;
		root.right = null;
		root = null;
	}
	
	public static int heightRecursive(Node root)
	{
		if(root==null)
			return 0;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		return 1+Math.max(leftHeight, rightHeight);
	}
	
	public static int heightInterative(Node root)
	{
		Node marker = new Node(null, null, Integer.MIN_VALUE);
		if(root==null)
			return 0;
		int height = 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		queue.offer(marker);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(curr==marker)
			{
				//we hit the endof current level , increment the level and put a marker for next level end.
				++height;
				if(!queue.isEmpty()) //if queue is empty then entire tree is traversed,then dont insert marker.
					queue.offer(marker);
			}			
			else
			{
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
			
		}
		return height;
	}
	
	/**
	 * level ordered traversals
	 * if the currently popped root is a left increment the count
	 * @param root
	 * @return
	 */
	public static int countLeafNodes(Node root)
	{
		if(root==null)
			return 0;
		int count = 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(isLeaf(root))
				++count;
			if(curr.left!=null)
				queue.offer(curr.left);
			if(curr.right!=null)
				queue.offer(curr.right);
		}
		
		return count;
	}
	
	public static boolean areTreesIdentical(Node root1, Node root2)
	{
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null || root2 == null)
			return false;
		return ((root1.data==root2.data) 
				&& areTreesIdentical(root1.left, root2.left)
				&& areTreesIdentical(root1.right, root2.right)
				);
	}
	
	public static boolean areTreesMirrors(Node root1, Node root2)
	{
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null || root2 == null)
			return false;
		return ((root1.data==root2.data) 
				&& areTreesMirrors(root1.left, root2.right)
				&& areTreesMirrors(root1.right, root2.left)
				);
	}
	
	public static void createMirror(Node root)
	{
		if(root==null)
			return;
		Node left = root.left;
		Node right = root.right;
		root.left = right;
		root.right = left;
		createMirror(left);
		createMirror(right);
		
	}
	
	public static void image(Node root)
	{
		if(root==null)
			return;
		
		image(root.left);
		image(root.right);
		
		Node temp = root.right;
		root.right = root.left;
		root.left = temp;
		
	}
	
	public static void depthOfEachNode(Node root)
	{
		if(root==null)
			return;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		int nodesInLongestPath = leftHeight+rightHeight+1;
		System.out.println(root+" , leftHeight="+leftHeight+" : rightHeight="+rightHeight+" : nodesInLongestPath="+nodesInLongestPath);
		depthOfEachNode(root.left);
		depthOfEachNode(root.right);
	}
	
	public static int diameter(Node root)
	{
		if(root==null)
			return 0;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		int nodesInLongestPath = leftHeight+rightHeight+1; //total nodes in the longest path in which current node lies
		
		int leftDiameter = diameter(root.left);
		int rightDiameter = diameter(root.right);
		int maxDiameter = Math.max(leftDiameter, rightDiameter); //find which tree is giving max diameter
		
		return Math.max(nodesInLongestPath, maxDiameter);	
	}
	
	/**
	 * level order traversal
	 * have maxSum,maxLevel,level and currSum
	 * if marker is reached, reset the currSum, check currSum>maxSum if yes maxSum = currSum, maxLevel = level
	 * else increment the currSum by current node data and push left and right
	 * 
	 * @param root
	 * @return
	 */
	public static int findLevelWithMaxSum(Node root)
	{		
		if(root==null)
			return 0;
		Node marker = new Node(null, null, Integer.MIN_VALUE);
		int maxSum = 0, maxLevel = 0;
		int level = 0;
		int currSum = 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		queue.offer(marker);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(curr==marker)
			{				
				if(currSum>maxSum)
				{
					maxSum = currSum;
					maxLevel = level;
				}				
				if(!queue.isEmpty())
					queue.offer(marker); //adding marker for next level					
				++level;				
				currSum = 0;//reset the currSum for the next level sum
			}
			else
			{
				currSum+=curr.data;
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
		}
		return maxLevel;
	}
	/**
	 * maintain a stack of current level path and push current root
	 * if the current node is leaf print stack
	 * traverse left and right
	 * pop the current from the stack before the stack unwinds onto parent node
	 * 
	 * @param root
	 * @param path
	 */
	public static void printAllRootToLeafPaths(Node root, Stack<Integer> path)
	{
		if(root==null)
			return;
		path.add(root.data);
		if(root.left==null && root.right==null)
		{
			System.out.println("Found a path = "+path);			
		}
		
		printAllRootToLeafPaths(root.left, path);
		printAllRootToLeafPaths(root.right, path);
		path.pop();
	}
	
	public static void printPathMatchingSum(Node root, Stack<Integer> path,int sum)
	{
		if(root==null)
			return;
		path.add(root.data);
		if(root.left==null && root.right==null)
		{
			int pathSum = 0;
			for(int i: path)
				pathSum+=i;
			if(pathSum==sum)
			{
				System.out.println("Found a path matching the sum= "+path+", sum = "+sum);
			}
						
		}
		
		printPathMatchingSum(root.left, path, sum);
		printPathMatchingSum(root.right, path, sum);
		path.pop();
	}
	
	public static int sum(Node root)
	{
		if(root==null)
			return 0;
		return (root.data + sum(root.left) + sum(root.right));
	}

	
	/**
	 * 
	 * 1. if either of the two is the parent of other then that node is LCA
	 * 2. traverse into left tree and assign it to left lca
	 * 3. traverse into right tree and assign it to right lca
	 * 4. leftLca==null or rightLca will be null if condition 2 is satisfied
	 * 5. if both are non null which means from the current root the recursion went down  
	 * 		and returned the left and right nodes itself,in which case return the current root
	 * 
	 * @param root
	 * @param left
	 * @param right
	 * @return
	 */
	public static Node lca(Node root, int left, int right)
	{
		if(root==null) return null; // Base condition
		
		//if either of the two is the parent of the other, or we have traversed down to one of the two nodes
		if(root.data==left || root.data==right) 
			return root;
		
		//find left/right subtree lca
		Node leftLca = lca(root.left, left, right);
		Node rightLca = lca(root.right, left, right);
		
		// If both of the above calls return Non-NULL, then one key
	    // is present in once subtree and other is present in other,
	    // So this node is the LCA
		if(leftLca!=null && rightLca!=null)
			return root;
		
		// Otherwise check if left subtree or right subtree is LCA
		return (leftLca!=null?leftLca:rightLca);
	}
	
	/**
	 * Inorder sequence: D B E A F C
		Preorder sequence: A B D E C F
		
		Idea:
		1. In the normal tree construction using the inorder array we use the mid=start+end/2
		2. In pre order traversals, it always visits the root first,
		3. Hence we consult the preorder array to find the current root and find its index in inorder arr
		4. so instead of mid its the index of element in preorder in inorder arr
	 * @param inorder
	 * @param preorder
	 * @param start
	 * @param end
	 * @return
	 */
	public static Node createTreeUsingPreAndInorderSequences(Integer[] inorder, Integer[] preorder)
	{
		if(inorder==null || preorder==null)
			return null;
		int preIndex = 0;
		return createTreeUsingPreAndInorderSequences(inorder, preorder, 0, inorder.length-1, preIndex);
	}
	private static Node createTreeUsingPreAndInorderSequences(Integer[] inorder, Integer[] preorder, Integer start, Integer end, Integer preIndex)
	{
		if(start>end || preIndex == preorder.length)
			return null;
		int data = preorder[preIndex++];
		Node current = new Node(null, null, data);
		
		if(start==end)
			return current;
		
		Integer inIndex = Utils.search(inorder, data);
		current.left = createTreeUsingPreAndInorderSequences(inorder, preorder, start, inIndex-1, preIndex);
		current.right = createTreeUsingPreAndInorderSequences(inorder, preorder, inIndex+1, end, preIndex);
		
		return current;
	}

	public static boolean isLeaf(Node root)
	{
		if(root==null)
			return false;
		return (root.left==null && root.right==null);
	}
	public static void printAllAncestors(Node root)
	{
		if(root==null || isLeaf(root))
			return;
		System.out.println(root);
		printAllAncestors(root.left);
		printAllAncestors(root.right);
	}
	public static void printAllLeaves(Node root)
	{
		if(root==null)
			return;
		if(isLeaf(root))
			System.out.println(root);
		printAllAncestors(root.left);
		printAllAncestors(root.right);
	}
	
	/**
	 * add next pointer to each tree node.
	 * do LOT and add current popped element.next = queue.peek()
	 * @param root
	 */
	public static void fillNextSiblings(Node root)
	{
		if(root==null)
			return;
		Node marker = new Node(Integer.MIN_VALUE);
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		queue.offer(marker);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(curr==marker)
			{
				if(!queue.isEmpty())
					queue.offer(marker);
			}
			else
			{
				if(queue.peek()!=marker)
				{
					curr.nextSibling = queue.peek();
				}
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @param root
	 */
	public static void printVerticalSum(Node root)
	{
		Map<Integer, Integer> levelSum = new HashMap<>();
		computeVerticalSum(root, levelSum, 0);
		levelSum.forEach((k,v)->{
			System.out.println("Vertical level = "+k+", sum = "+v);
		});
	}
	
	private static void computeVerticalSum(Node root, java.util.Map<Integer, Integer> levelSum, Integer level)
	{
		if(root==null)
			return;
		Integer sum = levelSum.get(level);
		if(sum==null)
			sum = new Integer("0");
		sum = sum + root.data;
		levelSum.put(level, sum);
		computeVerticalSum(root.left, levelSum, level-1);
		computeVerticalSum(root.right, levelSum, level+1);
	}
	
	/**
	 * 
	 * AVL tree check
	 * @param root
	 * @return
	 */
	public static boolean isHeightBalanced(Node root)
	{
		if(root==null)
			return true;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		int balance = Math.abs(leftHeight-rightHeight);
		return (balance<=1) && isHeightBalanced(root.left) && isHeightBalanced(root.right);
	}
	
	/**
	 * To create Double tree of the given tree, create a new duplicate for each node, and insert the duplicate as the left child of the original node.
	 * INPUT
		  		1
	          /   \
	        2      3
	      /  \
	    4     5
    	
    	OUTPUT:
		    			1
		             /   \
		           1      3
		          /      /
		        2       3
		      /  \
		     2    5
		    /    /
		   4   5
		  /   
		 4    
    
	 */
	
	public static void createDoubleTree(Node root)
	{
		if(root==null)
			return ;
		Node doubleRoot = new Node(root.data);
		doubleRoot.left = root.left;		
		root.left = doubleRoot;
		createDoubleTree(doubleRoot.left);
		createDoubleTree(root.right);	
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
	*  	 	 26
	        /   \
	      10     3
	    /    \     \
	  4      6      3
	 * @param root
	 * @return
	 */
	public static boolean checkIfSumTree(Node root)
	{
		if(root==null || TreeUtils.isLeaf(root))
			return true;
		int left = TreeUtils.sum(root.left);
		int right = TreeUtils.sum(root.right);
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
			 return TreeUtils.areTreesIdentical(mainRoot, subRoot);
		return checkIfSubtree(mainRoot.left, subRoot) || checkIfSubtree(mainRoot.right, subRoot); 			
	}

	/**
			 * 		 50
		           /     \     
		         /         \
		       7             2
		     / \             /\
		   /     \          /   \
		  3        5      1      30
		  
		  output:
					  50
			        /    \     
			      /        \
			    19           31
			   / \           /  \
			 /     \       /      \
			14      5     1       30
	 * @param root
	 */
	
	public static void convertToSumTree(Node root)
	{
		if(root==null)
			return;
		convertToSumTree(root.left);
		convertToSumTree(root.right);
		int childSum = (root.left!=null?root.left.data:0) 
						+ (root.right!=null?root.right.data:0);
		if(root.data<childSum)
			root.data=childSum;
		else
			increment(root,root.data-childSum);
	}
	
	static private void increment(Node root, int delta)
	{
		if(root.left!=null)
		{
			int nextDelta = Math.abs(root.left.data - delta);
			root.left.data = delta;
			increment(root.left,nextDelta);
		}
		else if(root.right!=null)
		{
			int nextDelta = Math.abs(root.right.data - delta);
			root.right.data = delta;
			increment(root.right,nextDelta);
		}
	}
	static public boolean isStrictTree(Node root)
	{
		if(root==null)
			return true;
		int children = 0;
		if(root.left!=null)
			++children;
		if(root.right!=null)
			++children;
		if(children!=0 || children!=2)
			return false;
		return isStrictTree(root.left) && isStrictTree(root.right);
	}
	
	static public void printKthLevelNodes(Node root, int k)
	{
		if(root==null || k<0)
			return;
		
		printKthLevelNodes(root.left, k-1);
		printKthLevelNodes(root.right, k-1);
		if(k==0)
			System.out.println(root.data);
	}
	
	static class PathWithMaxSum
	{
		private int maxSum = Integer.MIN_VALUE;
		
		public Stack<Integer> pathWithMaxSum(Node root)
		{
			Stack<Integer> path = new Stack<>();
			Stack<Integer> maxPath = new Stack<>();
			this.pathWithMaxSumUtils(root, path, maxPath);
			System.out.println(maxSum);
			return maxPath;
		}
		
		private void pathWithMaxSumUtils(Node root,Stack<Integer> path,Stack<Integer> maxPath)
		{
			if(root==null)
				return;
			path.push(root.data);
			if(isLeaf(root))
			{
				int pathSum = 0;
				for(int i:path)
					pathSum+=i;
				if(maxSum<pathSum)
				{
					maxSum=pathSum;
					maxPath.clear();
					path.forEach(e->{maxPath.push(e);});
				}
					
			}
			pathWithMaxSumUtils(root.left, path, maxPath);
			pathWithMaxSumUtils(root.right, path, maxPath);
			path.pop();
		}
	}
	
	public static void printBoundaryNodesAntiClockwise(Node root)
	{
		if(root==null)
			return;
		Node temp = root;
		Queue<Integer> boundaryNodesQ = new java.util.LinkedList<>();
		while(!isLeaf(temp))
		{
			boundaryNodesQ.offer(temp.data);
			temp=temp.left;
		}
		Queue<Node> queue = new java.util.LinkedList<>();
		Stack<Integer> stack = new Stack<Integer>();
		queue.offer(root);
		while(!queue.isEmpty())
		{
			temp = queue.poll();
			if(isLeaf(temp))
				stack.push(temp.data);
			if(temp.left!=null)
				queue.offer(temp.left);
			if(temp.right!=null)
				queue.offer(temp.right);
		}
		while(!stack.isEmpty())
			boundaryNodesQ.offer(stack.pop());
		temp = root.right;
		while(!isLeaf(temp))
		{
			stack.push(temp.data);
			temp = temp.right;
		}
		while(!stack.isEmpty())
			boundaryNodesQ.offer(stack.pop());
		System.out.println(boundaryNodesQ);
	}
	/**
	 * To create Double tree of the given tree, create a new duplicate for each node, and insert the duplicate as the left child of the original node.
	 * INPUT
		  		1
	          /   \
	        2      3
	      /  \
	    4     5
    	
    	OUTPUT:
		    			1
		             /   \
		           1      3
		          /      /
		        2       3
		      /  \
		     2    5
		    /    /
		   4   5
		  /   
		 4    
    
	 */
	
	public static void createDoubleTree(Node root)
	{
		if(root==null)
			return ;
		Node doubleRoot = new Node(root.data);
		doubleRoot.left = root.left;		
		root.left = doubleRoot;
		createDoubleTree(doubleRoot.left);
		createDoubleTree(root.right);	
	}
}
