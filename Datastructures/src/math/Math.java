package math;

public class Math 
{
	static public int gcd(int a, int b)
	{
		if(a==0)
			return b;
		if(a==1)
			return a;
		if(a>b)
			return gcd(a%b,b);
		return gcd(b%a,a);
	}
}
