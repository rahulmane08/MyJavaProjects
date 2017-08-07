package dynamicprogramming;

import java.util.Scanner;

public class UglyNumbers {
	static int[] uglyNumbers(int n)
	{
		int[] ugly = new int[n];
		int i2,i3,i5;
		i2=i3=i5=0;
		int ugly2, ugly3, ugly5;
		ugly2=ugly3=ugly5=1;		
		int currUgly = 1;
		int i=0;
		while(i<n)
		{		
			ugly[i++]=currUgly;
			if(currUgly==ugly2)
			{
				i2++;
				ugly2 = 2*i2;
			}
			if(currUgly==ugly3)
			{
				i3++;
				ugly3 = 3*i3;
			}
			if(currUgly==ugly5)
			{
				i5++;
				ugly5 = 5*i5;
			}				
			currUgly = Math.min(ugly2, Math.min(ugly3, ugly5));			
		}
		
		return ugly;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			System.out.print("Ugly Number of ");
			int n = scan.nextInt();		
			System.out.println(n+" ugly number is "+uglyNumbers(n)[n-1]);
		}
	}
	
}
