package backtracking;

import java.util.ArrayList;
import java.util.List;

public class KnightTour2 
{
	final private int N;
	final private int[][] board;
	final int MOVES;
	int[] validMoves_X = {1, 1,-1,-1,2, 2,-2,-2};
	int[] validMoves_Y = {2,-2, 2,-2,1,-1,1,-1};
	private int move = 0;
	
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
	private boolean solve(int move, int x, int y) {
		if(move==MOVES)
			return true;
		List<Index> options = getOptions(x, y);
		for(Index curr: options)
		{
			if(isSafe(curr.getX(), curr.getY()))
			{
				board[x][y]=move;
				
			}
		}
		
		return false;
	}
	
	private List<Index> getOptions(int x, int y)
	{
		List<Index> indexes = new ArrayList<>();
		for(int k=0;k<N;k++)
			indexes.add(new Index(x+validMoves_X[k],y+validMoves_Y[k]));		
		return indexes;
	}
	
	public KnightTour2()
	{
		N = 5;
		board = new int[N][N];
		MOVES = N*N;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				board[i][j]=-1;	
	}
}
