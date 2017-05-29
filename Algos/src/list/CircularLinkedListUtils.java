package list;

public class CircularLinkedListUtils 
{
	static public void recReverse(CircularLinkedList l)
	{
		recReverse(l.length()+1,null,l.head,1);
	}
	static public void recReverse(int length, Node prev, Node curr, int nodeCount)
	{
		if(nodeCount==length)
		{
			//head reached
			curr.setNext(prev);
			return;
		}		
		recReverse(length,curr,curr.getNext(),++nodeCount);
		if(prev!=null)
			curr.setNext(prev);
	}
	static public void reverse(CircularLinkedList l)
	{
		if(l==null || l.head==null)
			return;
		Node prev=l.head,curr= prev.getNext(),next= curr.getNext();
		
		while(curr!=l.head)
		{
			curr.setNext(prev);
			prev = curr;
			curr=next;
			next = next.getNext();
		}
		curr.setNext(prev);
		
	}
}
