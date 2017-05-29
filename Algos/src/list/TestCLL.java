package list;

public class TestCLL 
{
	public static void main(String[] args) {
		CircularLinkedList c = new CircularLinkedList();
		c.insert(1);
		c.insert(2);
		c.insert(3);
		c.insert(4);
		c.insert(5);
		c.insert(6);
		c.print();
		CircularLinkedListUtils.recReverse(c);
		c.print();
		System.out.println(c.length());
		CircularLinkedListUtils.reverse(c);
		c.print();
	}
}
