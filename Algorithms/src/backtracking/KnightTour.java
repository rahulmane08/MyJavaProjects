package backtracking;

public class KnightTour {
	final private int N;
	final private int[][] board;
	final int MOVES;
	int[] validMoves_X = {1, 1,-1,-1,2, 2,-2,-2};
	int[] validMoves_Y = {2,-2, 2,-2,1,-1,1,-1};
	private int move = 0;
	public KnightTour()
	{
		N = 5;
		board = new int[N][N];
		MOVES = N*N;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				board[i][j]=-1;	
	}
	
	private boolean isSafe(int i, int j)
	{
		return (i>=0 && i<N) && (j>=0 && j<N) && (board[i][j]==-1);
	}
	
	public void solve()
	{			
		board[0][0]=move;
		if(solve(++move, 0, 0))
			Print2DArray.print(board, N, N);
		else
			System.out.println("No Solution");
	}
	
	public boolean solve(int move, int x, int y)
	{
		if(move==MOVES)
			return true;
		
			for(int k=0;k<N;k++)
			{
				int next_X = validMoves_X[k]+x;
				int next_Y = validMoves_Y[k]+y;
				if(isSafe(next_X, next_Y))
				{
					board[x][y]=move;					
					
					if(solve(++move, next_X, next_Y))
						return true;
					
					board[x][y]=-1;					
				}
			}
			
		return false;	
		
	}
	public static void main(String[] args) {
		new KnightTour().solve();
	}
}
