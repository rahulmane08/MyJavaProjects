package pq;

import java.util.Arrays;
import static java.lang.Math.*;
public class Heap 
{
	private int[] elements;
	private int capacity;
	private boolean maxHeap;
	private int count=0;
	
	private int getBalancedCapacity(int capacity)
	{
		int pow = (int)pow(2, ceil(log(new Double(capacity).doubleValue())
				/log(new Double(2).doubleValue())));
		 if(pow==capacity)
			 return getBalancedCapacity(capacity+1);
		 return pow;
	}
	
	private void fill()
	{
		for(int i=0;i<this.elements.length;i++)
			  if(maxHeap)
				elements[i]=Integer.MIN_VALUE;
			  else
				  elements[i]=Integer.MAX_VALUE;
	}
	public Heap(int capacity, boolean maxHeap) {
		super();		
		this.capacity = getBalancedCapacity(capacity);
		this.elements = new int[this.capacity];
		this.maxHeap = maxHeap;
		this.count=0;
		fill();		
	}
	
	public Heap(int[] arr, boolean maxHeap) {
		this(arr.length,maxHeap);		
		this.count = arr.length;
		for(int i=0;i<arr.length;i++)
			this.elements[i] = arr[i];
	}
	/**
	 * @return the elements
	 */
	public int[] getElements() {
		return elements;
	}
	
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the maxHeap
	 */
	public boolean isMaxHeap() {
		return maxHeap;
	}
	

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	public void resize()
	{
		int[] elements = new int[2*capacity];
		fill();
		Arrays.copyOf(elements, capacity);
		this.capacity=2*capacity;
	}
	
	public int removeLast()
	{
		if(count>0)
		{			
			elements[count-1]=maxHeap?Integer.MIN_VALUE:Integer.MAX_VALUE;
			--count;
		}
		return count;
	}
	public int add(int elem)
	{
		if(count==capacity)
			resize();
		else
			elements[count]=elem;
		return count++;
	}
}
