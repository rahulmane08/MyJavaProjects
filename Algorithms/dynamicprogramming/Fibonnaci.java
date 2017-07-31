package dynamicprogramming;

public class Fibonnaci 
{
	//classic fib using recursion
	static int fib(int n)
	{
		if(n<=1)
			return n;
		return fib(n-1) + fib(n-2);
	}
	
	static int fibUsingDp(int n)
	{
		if(n<=1)
			return 1;
		int a = 0;
		int b = 1;
		int c = 0;
		for(int i=2;i<=n;i++)
		{
			c = a+b;
			a=b;
			b=c;
		}
		return c;
	}
	
	public static void main(String[] args) {
		System.out.println(fib(5));
		System.out.println(fibUsingDp(5));
	}
}
