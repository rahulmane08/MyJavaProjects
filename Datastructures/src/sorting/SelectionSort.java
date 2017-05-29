package sorting;

import utils.Swapper;

public class SelectionSort {
	public static void sort(int[] arr)
	{
		for(int i=0;i<arr.length-1;i++)
		{
			int minIndex = i;
			for(int j=i+1;j<arr.length;j++)			
				if(arr[minIndex]>arr[j])
					minIndex=j;
			if(arr[minIndex]!=i)
				Swapper.swap(arr, i, minIndex);
		}
	}
}
