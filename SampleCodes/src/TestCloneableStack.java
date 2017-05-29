
public class TestCloneableStack {
	public static void main(String[] args) throws CloneNotSupportedException {
		Stack stack = new Stack();
		for(int i=0;i<16;i++)
			stack.push(i);
		
		Stack stack2 = stack.clone();
		System.out.println(stack);
		System.out.println(stack2);
		stack.pop();
		System.out.println(stack);
		System.out.println(stack2);
	}
}
