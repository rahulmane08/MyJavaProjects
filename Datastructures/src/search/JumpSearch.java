package search;

public class JumpSearch {
	static public boolean search(int[] arr, int elem) {
		int n = arr.length;
		int jump = (int) Math.floor(Math.sqrt(n));
		int prev = 0;
		for (int i = 0; i < n;) {
			prev = i;
			if (elem < arr[jump])
				break;
			i = i + jump + 1;
			jump = Math.min(jump * 2, n);
		}
		for (int i = prev; i <= jump; i++)
			if (arr[i] == elem)
				return true;
		return false;
	}
}
