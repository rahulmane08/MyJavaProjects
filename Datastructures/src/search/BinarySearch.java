package search;

public class BinarySearch {
	static public boolean search(int[] arr, int left, int right, int elem) {
		if (right < left)
			return false;
		int mid = (left + right)>>>1;
		if (arr[mid] == elem)
			return true;
		else if (elem < arr[mid])
			return search(arr, left, mid - 1, elem);
		else
			return search(arr, mid + 1, right, elem);
	}

	static public boolean search(int[] arr, int elem) {
		return search(arr, 0, arr.length - 1, elem);
	}
}
