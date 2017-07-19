package sort;

import utils.Swapper;

public class HeapSort 
{
	static public void sort(int[] arr,boolean desc)
	{
		int n = arr.length;
		
		//first heapify the array
		for(int i=(n/2)-1;i>=0;i--)
			heapify(arr, i, n, desc);
		
		
		//swap last element with root and then heapify again
		for(int i=n-1;i>=0;i--)
		{
			Swapper.swap(arr, 0, i);
			heapify(arr, 0, i, desc);
		}
	}

	private static void heapify(int[] arr, int i, int n,boolean desc) 
	{
		int indexToReplace = i;
		int leftIndex = 2*i+1;
		int rightIndex = 2*i+2;
		
		if(leftIndex<n && (desc?arr[indexToReplace]>arr[leftIndex]:arr[leftIndex]>arr[indexToReplace]))
			indexToReplace = leftIndex;
		if(rightIndex<n && (desc?arr[indexToReplace]>arr[rightIndex]:arr[rightIndex]>arr[indexToReplace]))
			indexToReplace = rightIndex;
		
		if(indexToReplace!=i)
		{
			Swapper.swap(arr, indexToReplace, i);
			heapify(arr, indexToReplace, n, desc);
		}
	}
}
