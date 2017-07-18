package pq;

import utils.Swapper;

public class PriorityQueue 
{
	private Heap heap;
	

	public PriorityQueue(int capacity, boolean maxHeap) {
		super();
		heap = new Heap(capacity, maxHeap);
	}
	public PriorityQueue(int[] arr, boolean maxHeap) {
		super();
		heap = new Heap(arr, maxHeap);
		heapifyArray();
	}
	
	public int getParent(int index)
	{
		return heap.getElements()[getParentIndex(index)];
	}
	
	public int getSize()
	{
		return heap.getCount();
	}
	
	public boolean isMaxHeap()
	{
		return heap.isMaxHeap();
	}
	public int[] getElements()
	{
		int count = heap.getCount();
		int[] x = new int[count];		
		for(int i=0;i<count;i++)
			x[i]=heap.getElements()[i];
		return x;
		
	}
	public int getParentIndex(int index)
	{		
		if(index==0)
			return 0;
		if(validateIndex(index))
		{
			int parentIndex = (index-1)/2;
			if(validateIndex(parentIndex))
				return parentIndex;
		}
		return -1;
	}

	public boolean validateIndex(int index) {
		if(index<0 || index>getSize()-1)
			return false;
		return true;
	}
	
	public int getLeftChildIndex(int index)
	{
		if(validateIndex(index))
		{
			int leftIndex = 2*index+1;
			if(validateIndex(leftIndex))
				return leftIndex;
		}
		return -1;
	}
	
	public int getRightChildIndex(int index)
	{
		if(validateIndex(index))
		{			
			int rightIndex = 2*index+2;
			if(validateIndex(rightIndex))
				return rightIndex;
		}
		return -1;
	}
	
	public int getLeftChild(int index)
	{
		if(getLeftChildIndex(index)!=-1)
			return heap.getElements()[getLeftChildIndex(index)];
		return -1;
	}
	
	public int getRightChild(int index)
	{
		if(getRightChildIndex(index)!=-1)
			return heap.getElements()[getRightChildIndex(index)];
		return -1;
	}
	
	private void heapifyArray()
	{
		int lastParentIdx = getParentIndex(heap.getCount()-1);
		System.out.println(lastParentIdx);
		for(int i=lastParentIdx;i>=0;i--)
			heapify(i, true);
	}
	
	public void heapify(int index, boolean percolateDown)
	{
		if(!validateIndex(index))		
			return;
		
		int currElem = heap.getElements()[index];
		int indexToReplace = index;
		if(percolateDown)
		{
			//percolate down
			int lIndex = getLeftChildIndex(index);
			int rIndex = getRightChildIndex(index);
			
			boolean checkLeft = validateIndex(lIndex);
			boolean checkRight = validateIndex(rIndex);			
			
			if(!checkLeft && !checkRight)
				return; //reached the last level
			
			if(heap.isMaxHeap())
			{
				if(checkLeft && checkRight)
				{
					int leftElem = heap.getElements()[lIndex];
					int rightElem = heap.getElements()[rIndex];
					if(currElem<leftElem)
					{
						indexToReplace = lIndex;						
					}
					if(leftElem<rightElem)
					{
						indexToReplace = rIndex;
					}
				}
				else if(checkLeft)
				{
					int leftElem = heap.getElements()[lIndex];
					if(currElem<leftElem)
					{
						indexToReplace = lIndex;						
					}
				}				
				else if(checkRight)
				{
					int rightElem = heap.getElements()[rIndex];
					if(currElem<rightElem)
					{
						indexToReplace = rIndex;
					}
				}
				
			}
			else
			{
				if(checkLeft && checkRight)
				{
					int leftElem = heap.getElements()[lIndex];
					int rightElem = heap.getElements()[rIndex];
					if(currElem>leftElem)
					{
						indexToReplace = lIndex;						
					}
					if(leftElem>rightElem)
					{
						indexToReplace = rIndex;
					}
				}
				else if(checkLeft)
				{
					int leftElem = heap.getElements()[lIndex];
					if(currElem>leftElem)
					{
						indexToReplace = lIndex;						
					}
				}				
				else if(checkRight)
				{
					int rightElem = heap.getElements()[rIndex];
					if(currElem>rightElem)
					{
						indexToReplace = rIndex;
					}
				}
			}
			
			
		}
		else
		{
			//percolate up
			int parentIndex = getParentIndex(index);		
			if(!validateIndex(parentIndex))
				return;
			int parentElem = getParent(index);
			
			if(heap.isMaxHeap())
			{
				if(parentElem<currElem)
				{
					indexToReplace = parentIndex;					
				}
					
			}
			else
			{
				if(parentElem>currElem)
				{
					indexToReplace = parentIndex;
				}
					
			}
		}
		if(indexToReplace!=index)
		{		
			Swapper.swap(heap.getElements(), index, indexToReplace);
			heapify(indexToReplace,percolateDown);
		}
			
		
	}
	
	public int delete()
	{
		//delete the first element of the array which is either min or max
		//copy the last element in to first and set the last element as 0
		// heapify index=0
		if(heap.getCount()==0)
			return -1;		
		return delete(0);
	}
	
	public int delete(int index)
	{
		if(heap.getCount()==0 || !validateIndex(index))
			return -1;
		int[] elements = heap.getElements();
		int count = heap.getCount();
		int deletedElement = elements[index];
		elements[index] = elements[count-1];
		heap.removeLast();
		heapify(index,true);
		return deletedElement;
		
	}
	
	public void insert(int elem)
	{
		int index = heap.add(elem);
		heapify(index, false);
	}
	public int getMax()
	{
		if(heap.isMaxHeap())
			return heap.getElements()[0];
		else
		{	
			int count = heap.getCount();
			int[] elems = heap.getElements();
			int lastParentIdx = getParentIndex(count-1);
			int max = elems[lastParentIdx+1];
			for(int i=lastParentIdx+2;i<count;i++)
				if(max<elems[i])
					max=elems[i];
			return max;
		}
	}
	
	public int getMin()
	{
		if(!heap.isMaxHeap())
			return heap.getElements()[0];
		else
		{	
			int count = heap.getCount();
			int[] elems = heap.getElements();
			int lastParentIdx = getParentIndex(count-1);
			int min = elems[lastParentIdx+1];
			for(int i=lastParentIdx+2;i<count;i++)
				if(min>elems[i])
					min=elems[i];
			return min;
		}
	}
}
