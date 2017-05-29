package sort;

import utils.Swapper;

public class SelectionSort {
	static public void sort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int minIdx = i;
			for (int j = i + i; j < n; j++)
				if (arr[j] < arr[minIdx])
					minIdx = j;
			if (minIdx != i)
				Swapper.swap(arr, minIdx, i);
		}
	}
}
