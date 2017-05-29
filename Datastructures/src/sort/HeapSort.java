package sort;

import utils.Swapper;

public class HeapSort {
	static public void sort(int[] arr) {
		int n = arr.length;
		for (int i = (n / 2) - 1; i >= 0; i--)
			heapify(arr, i, false, true);
		for (int i = n - 1; i >= 0; i--) {
			Swapper.swap(arr, 0, i);
			heapify(arr, 0, false, true);
		}
	}

	static public void heapify(int[] arr, int index, boolean maxHeap, boolean percolateDown) {
		if (!validateIndex(index, arr))
			return;
		int currElem = arr[index];
		int indexToReplace = index;
		if (percolateDown) {
			int lIndex = getLeftChildIndex(arr, index);
			int rIndex = getRightChildIndex(arr, index);
			if (!(validateIndex(lIndex, arr) || validateIndex(rIndex, arr)))
				return;
			int leftElem = arr[lIndex];
			int rightElem = arr[rIndex];
			if (maxHeap) {
				if (currElem < leftElem) {
					indexToReplace = lIndex;
				}
				if (leftElem < rightElem) {
					indexToReplace = rIndex;
				}
			} else {
				if (currElem > leftElem) {
					indexToReplace = lIndex;
				}
				if (leftElem > rightElem) {
					indexToReplace = rIndex;
				}
			}
		} else {
			int parentIndex = getParentIndex(arr, index);
			if (!validateIndex(parentIndex, arr))
				return;
			int parentElem = arr[parentIndex];
			if (maxHeap) {
				if (parentElem < currElem) {
					indexToReplace = parentIndex;
				}
			} else {
				if (parentElem > currElem) {
					indexToReplace = parentIndex;
				}
			}
		}
		if (indexToReplace != index) {
			Swapper.swap(arr, index, indexToReplace);
			heapify(arr, indexToReplace, maxHeap, percolateDown);
		}
	}

	static private int getLeftChildIndex(int[] arr, int index) {
		if (validateIndex(index, arr)) {
			int leftIndex = 2 * index + 1;
			if (validateIndex(leftIndex, arr))
				return leftIndex;
		}
		return -1;
	}

	static private int getParentIndex(int[] arr, int index) {
		if (index == 0)
			return 0;
		if (validateIndex(index, arr)) {
			int parentIndex = (index - 1) / 2;
			validateIndex(parentIndex, arr);
			return parentIndex;
		}
		return -1;
	}

	static private int getRightChildIndex(int[] arr, int index) {
		if (validateIndex(index, arr)) {
			int rightIndex = 2 * index + 2;
			if (validateIndex(rightIndex, arr))
				return rightIndex;
		}
		return -1;
	}

	static private boolean validateIndex(int index, int[] arr) {
		if (index < 0 || index > arr.length - 1)
			return false;
		return true;
	}
}