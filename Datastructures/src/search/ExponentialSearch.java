package search;
/**
 * Time Complexity : O(Log n)
	Auxiliary Space : The above implementation of Binary Search is recursive and requires O()Log n) space. With iterative Binary Search, we need only O(1) space.
	
	Applications of Exponential Search:
	
	Exponential Binary Search is particularly useful for unbounded searches, where size of array is infinite. Please refer Unbounded Binary Search for an example.
	It works better than Binary Search for bounded arrays also when the element to be searched is closer to the first element.
 * @author manerah
 *
 */
public class ExponentialSearch {
	static public boolean search(int[] arr, int elem) {
		if (arr[0] == elem)
			return true;
		int n = arr.length;
		int i = 1;
		while (i < n && arr[i] <= elem)
			i = i * 2;
		return BinarySearch.search(arr, i / 2, Math.min(i, n - 1), elem);
	}
}
