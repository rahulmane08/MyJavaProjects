package list;

public class LinkedList 
{
	public Node start;
	
	
	public LinkedList() {
		super();
	}
	public LinkedList(Node start) {
		super();
		this.start = start;
	}
	public void insert(Node s, int data)
	{
		Node t = new Node(data);
		if(start==null)
		{
			start = t;
			return;
		}
		if(s.getNext()==null)
		{
			s.setNext(t);
			return;
		}
		insert(s.getNext(), data);	
			
	}
	public void print()
	{
		if(start==null)
			return;
		for(Node t=start;t!=null;t=t.getNext())
			System.out.print(t.getData()+" ");
		System.out.println();
	}
	
}
