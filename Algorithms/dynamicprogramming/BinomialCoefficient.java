package dynamicprogramming;


public class BinomialCoefficient 
{
	static int bce(int n,int k)
	{
		if(k==0 || k ==n)
			return 1;
		return bce(n-1,k-1) + bce(n-1,k);
	}	
}
