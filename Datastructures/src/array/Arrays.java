package array;

public class Arrays {
	static public void countPairsEqualToN(int[] arr, int N) {
		java.util.Arrays.sort(arr);// nlog(n)
		int end = arr.length - 1, start = 0;
		for (; arr[end] >= N; end--) {
		}
		int count = 0;
		while (start < end) {
			int sum = arr[start] + arr[end];
			if (sum == N) {
				++count;
				++start;
				--end;
			} else if (sum > N)
				--end;
			else
				++start;
		}
		System.out.println("Total Pairs = " + count);
	}

	static public void splitIntoEqualSumSubArrays(int[] arr) {
		java.util.Arrays.sort(arr); // nlog(n)
		int sum = 0;
		for (int i : arr)
			sum += i;
		int halfSum = sum / 2;
		int end = 0;
		for (end = 0; end < arr.length; end++) {
			sum = sum - arr[end];
			if (sum < halfSum) {
				System.out.println("no equal sum sub arrays found");
				return;
			} else if (sum == halfSum) {
				break;
			}
		}
		System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, 0, end + 1)));
		System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, end + 1, arr.length - 1)));
	}

	static public void printNonRepeatingElement(int[] arr) {
		int elem = 0;
		for (int i : arr)
			elem = elem ^ i;
		System.out.println("dupe = " + elem);
	}

	static public void printXOR(int[] arr) {
		int xorSum = 0;
		for (int i : arr)
			xorSum ^= i;
		int[] xor = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			xor[i] = arr[i] ^ xorSum;
		System.out.println(java.util.Arrays.toString(xor));
	}

	static public void toSubArraysWithEqualSum(int[] arr)

	{

		int end = 0;

		int sum = 0;

		int n = arr.length;

		for (int i : arr)

			sum += i;

		int halfSum = sum / 2;

		for (int i = 0; i < n; i++)

		{

			sum = sum - arr[i];

			if (sum == halfSum)

			{

				end = i;

				break;

			}

			else if (sum < arr[i])

			{

				System.out.println("no subarrays");

				return;

			}

		}

		System.out.println(
				"first subarray = " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, 0, end + 1)));

		System.out.println(
				"second subarray = " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, end + 1, n)));

	}

	static public int[][] createSnakeAndLadderPattern(int n)

	{

		int[][] board = new int[n][n];

		boolean odd = false;

		for (int i = 0; i < n; i++)

		{

			for (int j = 0; j < n; j++)

			{

				int elem = 5 * i + j + 1;

				if (!odd)

					board[i][j] = elem;

				else

					board[i][n - j - 1] = elem;

			}

			odd = !odd;

		}

		return board;

	}

	static public int[] findMinUnsortedSubArrayToSortEntireArray(int[] arr)

	{

		int n = arr.length;

		int start = 0;

		int end = n - 1;

		for (int i = 0; i < n; i++)

			if (arr[i] > arr[i + 1])

			{

				start = i;

				break;

			}

		for (int i = n - 1; i >= 0; i--)

			if (arr[i] < arr[i - 1])

			{

				end = i;

				break;

			}

		int min, max;

		min = max = arr[start];

		for (int i = start; i <= end; i++)

		{

			if (arr[i] < min)

				min = arr[i];

			if (max < arr[i])

				max = arr[i];

		}

		for (int i = 0; i < start; i++)

		{

			if (arr[i] > min)

			{

				start = i;

				break;

			}

		}

		for (int i = n - 1; i > end; i--)

		{

			if (arr[i] < max)

			{

				end = i;

				break;

			}

		}

		return java.util.Arrays.copyOfRange(arr, start, end + 1);

	}
}
