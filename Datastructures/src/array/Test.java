package array;

import java.util.Arrays;

public class Test 
{
	public static void main(String[] args) {
		/*Arrays.printNonRepeatingElement(new int[]{7,4,5,6,5,6,4});
		Arrays.printXOR(new int[]{2, 1, 5, 9});
		*/
		int[] a = {1,3,6};
		int[] b = {2,4,7};
		System.out.println("merged = "+Arrays.toString(array.Arrays.merge(a, b)));
	}
}
