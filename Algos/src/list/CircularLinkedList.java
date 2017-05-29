package list;

public class CircularLinkedList 
{
	public Node head;

	
	public CircularLinkedList() {
		super();
	}

	public CircularLinkedList(Node head) {
		super();
		this.head = head;
	}
	
	public void insert(int data)
	{
		Node n = new Node(data);
		n.setNext(n);
		if(head==null)
			head=n;
		else
		{
			Node c = head;
			while(c.getNext()!=head)
				c = c.getNext();
			n.setNext(head);
			c.setNext(n);
		}
	}
	
	public void print()
	{
		if(head==null)
			return;
		Node c = head;
		while(true)
		{
			System.out.print(c.getData()+" ");
			c=c.getNext();
			if(c==head)
				break;
		}
		System.out.println();
	}
	
	public int length()
	{
		if(head==null)
			return 0;
		int length = 0;
		Node curr = head;
		while(curr.getNext()!=null)
		{
			curr = curr.getNext();
			++length;
			if(curr==head)
				break;
		}
		return length;
	}
}
