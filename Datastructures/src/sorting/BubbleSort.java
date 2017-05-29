package sorting;

import utils.Swapper;

public class BubbleSort {
	public static void sort(int[] arr)
	{
		int round = 0;
		boolean swapped = true;
		while(swapped)
		{
			swapped=false;
			round++;
			for(int i=0;i<=arr.length-round-1;i++)
				if(arr[i]>arr[i+1])
				{
					swapped=true;
					Swapper.swap(arr, i, i+1);
					
				}
					
			
		}
		
			
	}
}
