package list;

public class CircularLinkedList 
{
	
	private Node start;

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}
	
	public void insert(int data)
	{
		if(start==null)
		{
			start=new Node(data);
			start.next=start;
		}
		else
		{
			Node n = new Node(data);
			n.next=start;
			Node last = getLast();
			last.next=n;			
		}
	}
	
	public Node getLast()
	{
		if(start==null)
			return null;
		Node last = start;
		while(last.next!=start)
			last=last.next;
		return last;
	}
	
	public void traverse()
	{
		Node curr=start;
		do
		{
			System.out.print(curr.data+" ");
			curr=curr.next;
		}
		while(curr!=start);
		System.out.println();
	}
	
	public void insertAtStart(int data)
	{
		if(start==null)
		{
			start=new Node(data);
			start.next=start;
		}
		else
		{
			Node last = getLast();
			Node n = new Node(data);
			n.next=start;
			start=n;
			last.next=n;
		}
	}
}
