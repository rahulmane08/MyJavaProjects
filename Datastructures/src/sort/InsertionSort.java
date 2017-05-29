package sort;

import utils.Swapper;

public class InsertionSort {
	static public void sort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1])
					Swapper.swap(arr, j - 1, j);
			}
		}
	}
}