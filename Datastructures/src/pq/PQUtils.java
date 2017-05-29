package pq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class PQUtils {
	static public int[] heapifyArray(int[] arr, boolean ascending) {

		if (arr != null && arr.length > 0) {
			PriorityQueue pq = new PriorityQueue(arr.length, !ascending);
			for (int i = 0; i < arr.length; i++)
				pq.insert(arr[i]);
			return pq.getElements();
		}
		return arr;
	}

	static PriorityQueue heapifyArray1(int[] arr, boolean ascending) {

		if (arr != null && arr.length > 0) {
			PriorityQueue pq = new PriorityQueue(arr.length, !ascending);
			for (int i = 0; i < arr.length; i++)
				pq.insert(arr[i]);
			return pq;
		}
		return null;
	}

	static public int[] heapifyArray2(int[] arr, boolean ascending) {

		if (arr != null && arr.length > 0) {
			PriorityQueue pq = new PriorityQueue(arr, !ascending);
			return pq.getElements();
		}
		return arr;
	}

	static public int getKthMinUsingAuxHeap(int k, PriorityQueue pq) {
		Queue<Integer> queue = new LinkedList<>();
		if (k > pq.getSize() || !pq.isMaxHeap())
			return -1;
		if (k == pq.getSize())
			return pq.getElements()[pq.getSize() - 1];
		PriorityQueue pqAux = new PriorityQueue(pq.getSize(), pq.isMaxHeap());
		pqAux.insert(pq.getMax());
		queue.offer(0);
		for (int i = 0; i < pq.getSize(); i++) {
			int currIdx = queue.poll();
			System.out.println(currIdx + ":" + pqAux.delete());
			queue.offer(pq.getLeftChildIndex(currIdx));
			queue.offer(pq.getRightChildIndex(currIdx));
			pqAux.insert(pq.getLeftChild(currIdx));
			pqAux.insert(pq.getRightChild(currIdx));
		}
		return 0;
	}

	static public List<Integer> allElementsLessThan(int k, PriorityQueue pq) {
		List<Integer> output = new ArrayList<>();
		allElementsLessThan(k, 0, pq, output);
		return output;
	}

	static public void allElementsLessThan(int k, int currentIdx, PriorityQueue pq, List<Integer> output) {
		if (!pq.validateIndex(currentIdx))
			return;
		int[] elements = pq.getElements();
		if (elements[currentIdx] <= k)
			output.add(elements[currentIdx]);

		if (!pq.isMaxHeap() && pq.getElements()[currentIdx] > k)
			return;

		allElementsLessThan(k, pq.getLeftChildIndex(currentIdx), pq, output);
		allElementsLessThan(k, pq.getRightChildIndex(currentIdx), pq, output);

	}

	static public void printStringWithNonRepeatingChars(String str) {
		Map<Character, Integer> charFreq = new HashMap<Character, Integer>();
		char[] charArr = str.toCharArray();
		for (char c : charArr) {
			if (charFreq.get(c) != null) {
				int count = charFreq.get(c);
				++count;
				charFreq.put(c, count);
			} else {
				charFreq.put(c, 1);
			}
		}
		java.util.PriorityQueue<Map.Entry<Character, Integer>> pq = new java.util.PriorityQueue<>(
				new java.util.Comparator<Map.Entry<Character, Integer>>() {
					@Override
					public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
						return o2.getValue().compareTo(o1.getValue());
					}
				});
		for (Map.Entry<Character, Integer> entry : charFreq.entrySet())
			pq.offer(entry);
		String output = "";
		Map.Entry<Character, Integer> prevEntry = null;
		while (!pq.isEmpty()) {
			Map.Entry<Character, Integer> currEntry = pq.poll();
			Character curr = currEntry.getKey();
			output += curr;
			if (prevEntry != null && prevEntry.getValue() > 0)
				pq.offer(prevEntry);
			currEntry.setValue(currEntry.getValue() - 1);
			prevEntry = currEntry;
		}
		if (output.length() != str.length()) {
			System.out.println("Not possible");
			return;
		}
		System.out.println(output);
	}

	/**
	 * 
	 * Input: [6, 8, 4, 5, 2, 3]
	 * 
	 * Output: 604
	 * 
	 * The minimum sum is formed by numbers
	 * 
	 * 358 and 246
	 * 
	 * 
	 * 
	 * Input: [5, 3, 0, 7, 4]
	 * 
	 * Output: 82
	 * 
	 * The minimum sum is formed by numbers
	 * 
	 * 35 and 047
	 * 
	 * @param arr
	 * 
	 * @return
	 * 
	 */
	static public int findMinSumOfTwoNumbers(int[] arr) {
		Comparator<Integer> c = (o1, o2) -> o1.compareTo(o2);
		java.util.PriorityQueue<Integer> pq1 = new java.util.PriorityQueue<>(c);
		java.util.PriorityQueue<Integer> pq2 = new java.util.PriorityQueue<>(c);
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0)
				pq1.offer(arr[i]);
			else
				pq2.offer(arr[i]);
		}
		String operand1 = "", operand2 = "";
		while (!pq1.isEmpty())
			operand1 += pq1.poll();
		while (!pq2.isEmpty())
			operand2 += pq2.poll();
		return Integer.parseInt(operand1) + Integer.parseInt(operand2);
	}

	static public int minCostToConnectNRopes(int[] arr) {
		java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<Integer>((o1, o2) -> o1.compareTo(o2));
		for (int i : arr)
			pq.offer(i);
		int minCost = 0;
		while (!pq.isEmpty()) {
			minCost = 0;
			minCost += pq.poll();
			if (pq.isEmpty())
				break;
			minCost += pq.poll();
			pq.offer(minCost);
		}
		return minCost;
	}

	static public boolean isHeap(int[] arr, boolean max) {
		return isHeap(arr, 0, arr.length, max);
	}

	static private boolean isHeap(int[] arr, int index, int elements, boolean max) {
		if (index > (elements - 2) / 2)
			return true;
		boolean yes = 
				(max ? 
				(arr[index] >= arr[2 * index + 1] && arr[index] >= arr[2 * index + 2])
				: 
				(arr[index] <= arr[2 * index + 1] && arr[index] <= arr[2 * index + 2])
				)
				&& isHeap(arr, 2 * index + 1, elements, max) 
				&& isHeap(arr, 2 * index + 2, elements, max);
		return yes;
	}
}
