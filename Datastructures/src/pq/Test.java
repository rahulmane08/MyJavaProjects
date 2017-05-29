package pq;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class Test 
{
	public static int getPow(int capacity)
	{
		 int pow = (int)pow(2, ceil(log(new Double(capacity).doubleValue())
					/log(new Double(2).doubleValue())));
		 if(pow==capacity)
			 return getPow(capacity+1);
		 return pow;
	}
	public static void main(String[] args) 
	{
		//descending
		int[] arr = new int[]{4,3,5,1,11,30,15,6,200,100};
		arr = PQUtils.heapifyArray(arr, false);				
		print(arr);
		
		PriorityQueue pq2 = PQUtils.heapifyArray1(arr, false);
		int size = pq2.getSize();
		System.out.println("max="+pq2.getMax()+", min="+pq2.getMin());
		
		PriorityQueue pq3 = PQUtils.heapifyArray1(arr, true);
		size = pq3.getSize();
		System.out.println("max="+pq3.getMax()+", min="+pq3.getMin());
		
		System.out.println(PQUtils.allElementsLessThan(30, pq3));
		System.out.println(PQUtils.allElementsLessThan(30, pq2));
		/*
		for(int i=0;i<size;i++)
			System.out.println(pq2.delete());
		
		System.out.println();
		//ascending
		arr = new int[]{4,3,5,1,11,30,15,6};
		arr = PQUtils.heapifyArray(arr, true);				
		print(arr);
		
		
		pq2 = PQUtils.heapifyArray1(arr, true);
		size = pq2.getSize();
		System.out.println("max="+pq2.getMax()+", min="+pq2.getMin());
		for(int i=0;i<size;i++)
			System.out.println(pq2.delete());*/
		
		/*int[] arr = new int[]{4,3,5,1,11,30,15,6,100,200};
		arr = PQUtils.heapifyArray2(arr, true);
		print(arr);*/
	}
	private static void print(int[] arr) {
		for(int i=0;i<arr.length;i++)
			System.out.println("index="+i+", elem="+arr[i]);
		System.out.println();
	}
}
