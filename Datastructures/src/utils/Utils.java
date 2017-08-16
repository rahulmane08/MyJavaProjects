package utils;

public class Utils 
{
	public static <T> int search(T [] arr, T elem)
	{
		for(int i=0;i<arr.length;i++)
			if(arr[i]==elem)
				return i;
		return -1;
	}
	
	public static <T> void swapReferences(T[] arr)
	{
		T temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
	}
	public static void print(int[][] arr, int col, int row)
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)			
				System.out.print((j==0?"|":"")+" "+arr[i][j]+" "+(j==col-1?"|":""));
			System.out.println();
		}
	}
	public static int min(int... x)
	{
		int res = x[0];
		for(int i=1;i<x.length;i++)
			res = Math.min(res, x[i]);
		return res;
	}
	
	public static int max(int... x)
	{
		int res = x[0];
		for(int i=1;i<x.length;i++)
			res = Math.max(res, x[i]);
		return res;
	}
	
	public static int countDigits(int n)
	{
		int digits = 0;
		int rem = Math.abs(n);
		do{
			++digits;
			rem = rem/10;
		}
		while(rem>0);
		return digits;
	}
	
	public static void main(String[] args) {
		int n =73849949;
		System.out.println(mostSignificantDigit(n,countDigits(n)));
	}
	
	public static int mostSignificantDigit(int n, int digits)
	{
		if(digits==1)
			return n;
		int rem = Math.abs(n);
		do{
			rem = rem/10;
			--digits;
		}
		while(digits!=1);
		return rem;
	}
}
