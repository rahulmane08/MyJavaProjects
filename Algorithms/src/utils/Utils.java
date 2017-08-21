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
	
	static final int tableSizeFor(int cap, int MAXIMUM_CAPACITY) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
	
	static final int hash(int code)
	{
		int hashcode = code ^ (code >>> 16);
		return hashcode;
	}
	
	public static void main(String[] args) {
		System.out.println(tableSizeFor(4, 1 << 30));
		System.out.println(hash(2));
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
	
	public static boolean isValidArrayIndex(int x, int y, int M, int N)
	{
		if(x<0 || x>=M || y<0 || y>=N)
			return false;
		return true;
	}
}
