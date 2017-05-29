package list;

public class LinkedListUtils 
{
	public void print(LinkedList list)
	{
		if(list.start==null)
			return;
		for(Node t=list.start;t!=null;t=t.getNext())
			System.out.print(t.getData()+" ");
		System.out.println();
	}
	
	static public void recursivePrint(Node start)
	{
		if(start==null)
			return;
		System.out.println(start.getData());
		recursivePrint(start.getNext());
	}
	static public void recursiveRevPrint(Node start)
	{
		if(start==null)
			return;		
		recursiveRevPrint(start.getNext());
		System.out.println(start.getData());
	}
	static public int length(Node start)
	{
		if(start==null)
			return 0;
		return 1+length(start.getNext());
	}
	
	static public void reverse(LinkedList l)
	{
		if(l.start==null || l.start.getNext()==null)
			return;
		Node prev=null,curr=l.start,next=l.start.getNext();
		while(next!=null)
		{
			curr.setNext(prev);
			prev=curr;
			curr=next;
			next = next.getNext();
			
		}
		curr.setNext(prev);
		
		l.start = curr;
	}
	static public void recursiveRev(LinkedList l)
	{
		recursiveRev(l,null,l.start);
	}
	static public void recursiveRev(LinkedList l,Node prev, Node curr)
	{
		if(curr==null)
		{
			l.start = prev;
			return;
		}
		recursiveRev(l, curr, curr.getNext());
		curr.setNext(prev);
	}
	/**
	 * 1->4->5
	 * 2->3->6
	 * first try to find which node is the head of the merged list
	 * t1=1
	 * t2=3
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	static public LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2)
	{
		if((l1==null || l1.start==null) && (l2==null || l2.start==null))
			return null;
		else if(l1==null || l1.start==null)
			return l2;
		else if(l2==null || l2.start==null)
			return l1;
		else
		{
			
			Node curr1=l1.start,curr2=l2.start;
			Node head=null;
			if(curr1.getData()<curr2.getData())
			{
				head = curr1;
				curr1=curr1.getNext();
			}				
			else
			{
				head = curr2;
				curr2=curr2.getNext();
			}
			Node temp=head;	
			while(curr1!=null && curr2!=null)
			{
				if(curr1.getData()<curr2.getData())
				{
					temp.setNext(curr1);
					curr1=curr1.getNext();
				}
				else
				{
					temp.setNext(curr2);
					curr2=curr2.getNext();
				}
				temp = temp.getNext();
			}
			if(curr1==null)
			{
				temp.setNext(curr2);
			}
			if(curr2==null)
			{
				temp.setNext(curr1);
			}
			return new LinkedList(head);
		}
		
	}
	
	static public Node recursiveMergeTwoLists(Node start1, Node start2)
	{
		if(start1==null) return start2;
		if(start2==null) return start1;
		if(start1.getData()<start2.getData())
		{
			start1.setNext(recursiveMergeTwoLists(start1.getNext(), start2));
			return start1;
		}
		else
		{
			start2.setNext(recursiveMergeTwoLists(start2.getNext(), start1));
			return start2;
		}
	}
	
	static public void deleteNthNode(LinkedList l, int position)
	{
		if(position<1 || l==null || l.start==null)
			return;
		Node t=null;
		if(position==1)
		{
			System.out.println("deleting head node");
			t = l.start;
			l.start=l.start.getNext();
			t=null;
			return;
		}
		int length = length(l.start);
		
		if(position<=length)
		{
			Node curr = l.start;
			for(int i=1;i<position-1;i++)
				curr = curr.getNext();
			System.out.println("deleting "+position+"th node");
			t = curr.getNext();
			curr.setNext(t.getNext());
			t = null;
		}
	}
}
