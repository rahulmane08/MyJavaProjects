package list;

public class Test {
	public static void main(String[] args) 
	{
		System.out.println("add(110,229) = "+ListUtils.add(110, 229));
		System.out.println("subtract(110,229) = "+ListUtils.subtract(110, 229));
		
		LinkedList<Integer> list = new LinkedList<>(new Integer[]{1,2,3,4});
		ListUtils.reverse(list);
		System.out.println("reversified = "+list);
		
		ListUtils.reverseIterative(list);
		System.out.println("reversified iteratively = "+list);
		
		ListUtils.reversePairs(list);
		System.out.println("reversified pairs = "+list);
		
		list = new LinkedList<>(new Integer[]{2,3,7,3,2,12,24});
		System.out.println("longest palindrom = "+ListUtils.lengthOfLargestPalindrome(list));
		list = new LinkedList<>(new Integer[]{2,3,7,3,2,12,24});
		/*ListUtils.partitionlist(list, 3);
		System.out.println("after partitioning by 3 = "+list);*/
		
		list = new LinkedList<>(new Integer[]{1,2,3,4,5,6,7});
		ListUtils.rotateBy(list, 4);
		System.out.println("rotate by 4 = "+list);
		
		list = new LinkedList<>(new Integer[]{4,3,2,1});
		LinkedList<Integer> sortedList = ListUtils.mergeSort(list);
		System.out.println("after merge sort = "+sortedList);
		
		/*ListUtils.unionIntersection(new LinkedList<Integer>(new Integer[]{10,15,4,20})
									, new LinkedList<Integer>(new Integer[]{8,4,2,10}));*/
		
		list = new LinkedList<>(new Integer[]{1,2,3,4,5,6,7});
		ListUtils.reverse(list,3,2);
		System.out.println("reversing only 3 nodes starting from index 2 = "+list);
		
		list = new LinkedList<>(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		ListUtils.reverseAlternativelyAfterK(list, 3);
		System.out.println("reversing alternatively after every 3 nodes = "+list);
	}
}
