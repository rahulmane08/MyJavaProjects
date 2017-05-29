package list;

public class TestLinkedList 
{
	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.insert(l.start, 2);
		l.insert(l.start, 3);
		l.insert(l.start, 4);
		l.print();
//		LinkedListUtils.recursiveRevPrint(l.start);
		System.out.println("length="+LinkedListUtils.length(l.start));
		LinkedListUtils.reverse(l);
		l.print();
		LinkedListUtils.recursiveRev(l);
		l.print();
		
		LinkedList l1 = new LinkedList();
		l1.insert(l1.start, 1);
		l1.insert(l1.start, 6);
		l1.insert(l1.start, 7);
		
		LinkedList mergedList = LinkedListUtils.mergeTwoSortedLists(l, l1);
		mergedList.print();
		
		LinkedList l2 = new LinkedList();
		l2.insert(l2.start, 1);
		l2.insert(l2.start, 6);
		l2.insert(l2.start, 7);
		
		LinkedList l3 = new LinkedList();
		l3.insert(l3.start, 2);
		l3.insert(l3.start, 3);
		l3.insert(l3.start, 4);
		
		LinkedList recMergedList = new LinkedList(LinkedListUtils.recursiveMergeTwoLists(l2.start, l3.start));
		recMergedList.print();
		
		//deletions
		LinkedListUtils.deleteNthNode(recMergedList, 4);
		recMergedList.print();
	}
}
