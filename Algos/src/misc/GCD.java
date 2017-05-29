package misc;

public class GCD {
	public static int gcd(int num1, int num2)
	{
		if(num1==num2)
			return num1;
		
		if(num1>num2)
		{
			int result = num1%num2;
			if(result==0)
				return num2;
			else
				return gcd(num2, result);
		}
		else
		{
			int result = num2%num1;
			if(result==0)
				return num1;
			else
				return gcd(num1, result);
		}		
	}
	public static void main(String[] args) {
		System.out.println(gcd(16,14));
		System.out.println(gcd(14,16));
		System.out.println(gcd(16,8));
		System.out.println(gcd(16,15));
	}
}
