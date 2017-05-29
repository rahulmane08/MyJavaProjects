package sorting;

import utils.Swapper;

public class InsertionSort {
	public static void sort(int[] arr)
	{
		int length = arr.length;
		for(int i=0;i<length-1;i++)
			for(int j=i=1;j>=1;j--)
				if(arr[j]<arr[j-1])
					Swapper.swap(arr, i, j);
	}
}
