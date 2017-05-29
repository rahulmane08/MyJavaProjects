package sorting;


public class TestSorting {
	public static void main(String[] args) {
		int [] test = {5,4,3,2,1};
//		InsertionSort.sort(test);
//		SelectionSort.sort(test);
//		QuickSort.sort(test, 0, test.length-1);
		BubbleSort.sort(test);
		print(test);
	}
	public static void print(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
}
