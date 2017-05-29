package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PigeonholeSort {
	static public void sort(int[] arr) {
		int min = arr[0], max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		int range = min - max + 1;
		TreeMap<Integer, List<Integer>> holes = new TreeMap<>();
		for (int i = 0; i < arr.length; i++) {
			List<Integer> elements = holes.get(arr[i] - min);
			if (elements == null) {
				elements = new ArrayList<>();
				holes.put(arr[i] - min, elements);
			}
			elements.add(arr[i]);
		}
	}
}