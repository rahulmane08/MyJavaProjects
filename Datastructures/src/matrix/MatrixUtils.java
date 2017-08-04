package matrix;
import utils.Utils;
public class MatrixUtils {
	static public void rotateMatrix(int mat[][], int N)
	{
	    // Consider all squares one by one
	    for (int x = 0; x < N / 2; x++)
	    {
	        // Consider elements in group of 4 in 
	        // current square
	        for (int y = x; y < N-x-1; y++)
	        {
	            // store current cell in temp variable
	            int temp = mat[x][y];
	 
	            // move values from right to top
	            mat[x][y] = mat[y][N-1-x];
	 
	            // move values from bottom to right
	            mat[y][N-1-x] = mat[N-1-x][N-1-y];
	 
	            // move values from left to bottom
	            mat[N-1-x][N-1-y] = mat[N-1-y][x];
	 
	            // assign temp to left
	            mat[N-1-y][x] = temp;
	        }
	    }
	}
	
	
	/**
	 *  1     2     3     4
	    5     6     7     8
	    9    10    11    12
	   13    14    15    16
	   17    18    19    20
		Diagonal printing of the above matrix is
	    1
	    5     2
	    9     6     3
	   13    10     7     4
	   17    14    11     8
	   18    15    12
	   19    16
	   20
	 * @param m
	 * @param n
	 * @param a
	 */
	static public void diagonalPrint(int m, int n, int matrix[][])
	{
		 int LINES = m+n-1;
		
		for(int line=1;line<=LINES;line++)
		{
			int col_idx = Math.max(0, line-m);
			int row_idx = Math.min(m-1, line-1);
			int elements = Utils.min(line,  n-col_idx, m);;
//			System.out.println(line+","+row_idx+","+col_idx+","+elements);
			for(int i=0;i<elements;i++)
			{
				int x = (row_idx-i);
				int y = Math.abs(i-col_idx);
//				System.out.print(x+""+y+" ");
				System.out.print(matrix[x][y]+" ");
			}
			System.out.println();
		}
	}
	
	
	static void spiralPrint(int m, int n, int a[][])
    {
        int i, k = 0, l = 0;
        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */
          
        while (k < m && l < n)
        {
            // Print the first row from the remaining rows
            for (i = l; i < n; ++i)
            {
                System.out.print(a[k][i]+" ");
            }
            k++;
  
            // Print the last column from the remaining columns 
            for (i = k; i < m; ++i)
            {
                System.out.print(a[i][n-1]+" ");
            }
            n--;
  
            // Print the last row from the remaining rows */
            if ( k < m)
            {
                for (i = n-1; i >= l; --i)
                {
                    System.out.print(a[m-1][i]+" ");
                }
                m--;
            }
  
            // Print the first column from the remaining columns */
            if (l < n)
            {
                for (i = m-1; i >= k; --i)
                {
                    System.out.print(a[i][l]+" ");
                }
                l++;    
            }        
        }
    }
	static public int findPairWithMaxDiff(int mat[][], int N)
	{
		int[][] maxArray = new int[N][N];
		int maxDiff = Integer.MIN_VALUE;
		
		int max = mat[N-1][N-1]; //init max to last element
		
		
		/* mat[3][3]
		 * 1,2,3
		 * 4,5,6
		 * 7,8,9
		 * 
		 * maxArr[3][3]
		 * 0,0,0
		 * 0,0,0
		 * 0,0,9
		 */
		
		
		

		//fill the entire last row of maxArr with the max element of last row in actual arr
		for(int j=N-2;j>=0;j--)
		{
			if(mat[N-1][j]>max)
				max = mat[N-1][j];
			maxArray[N-1][j] = max;
		}	
		
		
		for(int j=N-2;j>=0;j--)
		{
			if(mat[j][N-1]>max)
				max = mat[j][N-1];
			maxArray[j][N-1] = max;
		}
		
		/**
		 * maxArr[3][3]
		 * 0,0,9
		 * 0,0,9
		 * 9,9,9
		 */
		
		 for (int i = N-2; i >= 0; i--)
		    {
		        for (int j = N-2; j >= 0; j--)
		        {
		            // Update maxValue
		            if (maxArray[i+1][j+1] - mat[i][j] > max)
		                max = maxArray[i + 1][j + 1] - mat[i][j];
		 
		            // set maxArr (i, j)
		            maxArray[i][j] = Math.max(mat[i][j], Math.max(maxArray[i][j + 1],maxArray[i + 1][j]) );
		        }
		    }
		
		return maxDiff;
	}
}
