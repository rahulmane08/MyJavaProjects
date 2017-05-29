package sort;

import utils.Swapper;

public class QuickSort {
	static public void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	static public void sort(int[] arr, int low, int high) {
		if (low >= high)
			return;
		int mid = (low + high) / 2;
		int pivot = arr[mid];
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (pivot < arr[j])
				j--;
			if (i <= j) {
				Swapper.swap(arr, i, j);
				i++;
				j--;
			}
		}
		sort(arr, low, j);
		sort(arr, i, high);
	}

	static public void sortIteratively(int[] arr) {
		int n = arr.length;
		int low = 0;
		int high = n - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			int pivot = arr[mid];
			int i = low, j = high;
			while (i <= j) {
				while (arr[i] < pivot)
					i++;
				while (pivot < arr[j])
					j--;
				if (i <= j) {
					Swapper.swap(arr, i, j);
					i++;
					j--;
				}
			}
		}
	}
}
