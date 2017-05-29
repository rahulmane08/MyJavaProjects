package sort;

import utils.Swapper;

public class BubbleSort {
	static public void sort(int[] arr) {
		int n = arr.length;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					Swapper.swap(arr, i, i + 1);
					swapped = true;
				}
			}
		}
	}
}
