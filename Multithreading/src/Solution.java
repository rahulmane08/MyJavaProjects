
public class Solution {
	/**
	   * int[] addFractions ( int[] fraction1, int[] fraction2 )
	   *
	   * Given two fractions passed in as int arrays,
	   * returns the fraction which is result of adding the two input fractions.
	   * Fraction is represented as a two-element array - [ numerator, denominator ]
	   * The returned fraction has to be in its simplest form.
	   */
	  public static int[] addFractions( int[] fraction1, int[] fraction2 ) {
	   
	    return ( new int[]{ 0, 0 } );
	  }

	  /**
	   * boolean doTestsPass()
	   * Returns true if all the tests pass. Otherwise returns false.
	   */
	  public static boolean doTestsPass() {
	    // TODO: implement some tests, please
	    // we've included a trivial boilerplate

	    int[] result = addFractions( new int[]{ 2, 3 }, new int[]{ 1, 2 } );

	    if( result[ 0 ] == 7 && result[ 1 ] == 6 ) {
	      System.out.println( "Test passed." );
	      return true;
	    } else {
	      System.out.println( "Test failed." );
	      return false;
	    }
	  }

	  public static void main( String[] args ) {
	    doTestsPass();
	  }
}
