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
}
