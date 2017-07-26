package numbertheory;

public class Math {
	static public double power(double x, int y)
	{
		if(y==0)
			return 1;
		if(y==1)
			return x;
		double temp = power(x, y/2);
		if(y%2==0)
			return temp*temp;
		else{
			
			if(y>0)
				return x*temp*temp;
			else
				return (temp*temp)/x;
		}
			
			
	}
	
	static public int gcd(int a, int b)
	{
		if(a==0)
			return  b;
		if(a>b)
			return gcd(a%b,b);
		else 
			return gcd(b%a,a);
	}
	
	static public int largestNonIncreasingNumberLessThan(int N)
	{
		String s = ""+N;
		int[] output = new int[s.length()];
		char[] c = s.toCharArray();
		for(int i=c.length-1;i>0;i--)
		{
			int curr = Integer.parseInt(String.valueOf(c[i]));
			int next = Integer.parseInt(String.valueOf(c[i-1]));
			if(curr<=next)
			{
				curr = 9;
				--next;				
				output[i-1]=next;
			}
			else
			{
				--curr;
			}
			output[i]=curr;
		}
		
		int result = 0;
		for(int i=output.length-1,j=0;i>=0;i--,j++)
		{
			result += output[i]*Math.power(10, j);
		}
		return result;
	}
	
	
	/**
	 * Let’s us take an example of 19

		1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,15,17,18,19,20,21,……
		1,3,5,7,9,11,13,15,17,19,…..
		1,3,7,9,13,15,19,……….
		1,3,7,13,15,19,………
		1,3,7,13,19,………
		hence forth from 6th iteration onwards the the first 5 remain intact , and hence theres no point continuing so we break.
	 */
	
	static public boolean isLucky(int n)
	{
		int counter = 2;
		return isLuckyUtil(n, counter);
	}
	static public boolean isLuckyUtil(int n, int counter)
	{
		if(counter>n)
			return true;
		if(n==counter)
			return false;
		int nextPos = n - (n/counter);
		return isLuckyUtil(nextPos,++counter);
	}
	
	static int multiplyBySeven(int n)
	{  
	    int x = (int) Integer.toUnsignedLong(n);
	    return ((n<<3) - n);
	}
	
}
