package dynamicprogramming;

public class LargestContiguousSubArraySum 
{
	public int maxSum(int[] arr)
	{
		if(arr==null || arr.length==0)
			return -1;
		int maxSoFar = arr[0], currMax=arr[0];
		
		for(int i=1;i<arr.length;i++)
		{
			currMax += arr[i];
			if(currMax<0)
				currMax=0;
			else if(maxSoFar<currMax)
				maxSoFar = currMax;				
		}
		
		return maxSoFar;
	}
}
