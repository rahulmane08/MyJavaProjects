package sorting;

import utils.Swapper;

public class QuickSort {
	public static void sort(int[] arr, int left, int right)
	{
		int i = left;
		int j = right;
		int pivot = (left+right)/2;
		
		while(i<=j)
		{
			while(arr[i]<=arr[pivot])
				i++;
			while(arr[j]>arr[pivot])
				j--;
			
			if(i<=j)
			{
				Swapper.swap(arr, i, j);
				i++;
				j--;
			}
		}
		
		if(left<j)
			sort(arr, left, j);
		
		if(i<right)
			sort(arr, i, right);
	}
}
