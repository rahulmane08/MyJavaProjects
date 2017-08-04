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
}
