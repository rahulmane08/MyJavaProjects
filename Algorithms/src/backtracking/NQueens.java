/**
 *  1) Start in the leftmost column
	2) If all queens are placed
	    return true
	3) Try all rows in the current column.  Do following for every tried row.
	    a) If the queen can be placed safely in this row then mark this [row, 
	        column] as part of the solution and recursively check if placing  
	        queen here leads to a solution.
	    b) If placing queen in [row, column] leads to a solution then return 
	        true.
	    c) If placing queen doesn't lead to a solution then umark this [row, 
	        column] (Backtrack) and go to step (a) to try other rows.
	3) If all rows have been tried and nothing worked, return false to trigger 
	    backtracking.
 * @author manerah
 *
 */
public class NQueens {
	final int N;
	final int[][] board;

	public NQueens(int boardSize) {
		super();
		N = boardSize;
		board = new int[N][N];
	}
	
	/**
	 * \
	 *  \ 
	 *   \
	 *    \
	 *     \
	 * ----- check the following pattern : left columns, upper left diagonal, lower left diagonal
	 * 		/
	 * 	   /
	 *    /
	 *   /
	 *  /
	 * /    
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isSafe(int row , int col)
	{
		for(int i=0;i<col;i++)
			if(board[row][i]==1)
				return false;
		for(int i=row,j=col;i>=0 && j>=0;i--,j--)
			if(board[i][j]==1)
				return false;
		for(int i=row,j=col;i<=N && j>=0;i++,j--)
			if(board[i][j]==1)
				return false;
		return true;
	}
	
	public void solve()
	{
		if(solve(0))
		{
			
		}
	}
	
	private boolean solve(int column)
	{
		if(column>=N)
			return true;
		else
		{
			for(int i=0;i<N;i++)
			{
				if(isSafe(i, column))
					board[i][column]=1;
				
				if(solve(column+1))
					return true;
				
				board[i][column]=0;
			}
		}
		return false;
	}
	
}
