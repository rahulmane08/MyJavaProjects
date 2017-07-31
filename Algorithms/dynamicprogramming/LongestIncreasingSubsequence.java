package dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence 
{	
	
	/**
	 * Code is correct , but the brute force times out in Hackerrank.
	 * 
	 * @param arr
	 * @return
	 */
	static public int[] lisBruteForce(int[] arr)
	{
		int max = 0;
		
		int n = arr.length;
		int[] longestSeq = new int[n];
		for(int i=0;i<n;i++)
			longestSeq[i]=1;
		
		for(int i=n-1;i>=0;i--)
		{
			int lis = longestSeq[i];
			for(int j=i+1; j<n; j++)
			{
				if(arr[i]<arr[j] && longestSeq[i]<=longestSeq[j])
				{
					lis = Math.max(longestSeq[i], 1+longestSeq[j]);
					longestSeq[i]=lis;
				}
			}
			max = Math.max(max, lis);
		}
		System.out.println("length of max increasing subsequence = "+max);
		
		return longestSeq;
	}
	
	public static void main(String[] args) {
//		System.out.println(Arrays.toString(lisBruteForce(new int[]{10,20,1,2,3,4,5,6,7,21})));
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();		
		int [] arr = new int[n];
		for(int i=0;i<n && scan.hasNext();i++)
			arr[i]=scan.nextInt();
		System.out.println(Arrays.toString(lisBruteForce(arr)));
	}
}
