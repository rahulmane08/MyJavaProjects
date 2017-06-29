package backtracking;

public class Sudoku 
{
	private int[][] board;
	private int N=9;
	private static final int UNASSIGNED = 0;
	public Sudoku() {
		super();		
		this.board = new int[9][9];		
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				board[i][j]=UNASSIGNED;
	}
	
	public void fill(int row,int col, int num)
	{
		if(num<1 || num>9)
			throw new IllegalArgumentException("number should be between 1 to 9");
		board[row][col]=num;
	}
	
	private boolean checkRowPresence(int row,int num)
	{
		for(int i=0;i<N;i++)
			if(board[row][i]==num)
				return true;
		return false;
	}
	
	private boolean checkColumnPresence(int col,int num)
	{
		for(int i=0;i<N;i++)
			if(board[i][col]==num)
				return true;
		return false;
	}
	
	private boolean checkBoxPresence(int rowStart,int colStart, int num)
	{
		for(int i=0;i<3;i++)
			for(int j=0; j<3; j++)
				if(board[i+rowStart][j+colStart]==num)
					return true;
		return false;
	}
	
	private boolean checkUnassignedCellPresence()
	{
		for(int i=0;i<N;i++)
			for(int j=0; j<N; j++)
				if(board[i][j]==UNASSIGNED)
					return true;
		return false;
	}
	
	private boolean isValid(int row,int col)
	{
		int num = board[row][col];
		return !(checkRowPresence(row, board[row][col]) || checkColumnPresence(col, board[row][col]) || checkBoxPresence(row-row%3,col-col%3, num));
	}
	
	
	public boolean solve(int row, int col)
	{
		if(!checkUnassignedCellPresence())
			return true;
		
		return false;
	}
	
}
