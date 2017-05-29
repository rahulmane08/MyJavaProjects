package search;

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
