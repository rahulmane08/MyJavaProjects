package backtracking;

public class RatMaze 
{
	final private int[][] maze;
	private int[][] solution;
	final int row, col;
	public RatMaze(int[][] maze,int row, int col) {
		super();
		this.maze = maze;
		this.row = row;
		this.col = col;
		solution = new int[row][col];
	}
	
	public void solve()
	{
		if(solve(0,0))
		{
			System.out.println("Solution:");
			Print2DArray.print(maze, row, col);
		}
		else
			System.out.println("Solution not possible");
	}
	
	private boolean isSafe(int row , int col)
	{
		return (row>=0 && row<this.row) && (col>=0 && col<this.col) && maze[row][col]==1;
	}
	
	public boolean solve(int row, int col)
	{
		if(row==this.row-1 && col==this.col-1)
		{
			solution[row][col]=1;
			return true;
		}			
			
		//move forward
		if(isSafe(row+1, col))
		{
			solution[row+1][col]=1;
			if(solve(row+1, col))
				return true;
			solution[row+1][col]=0;
		}
		
		//move downward
		else if(isSafe(row, col+1))
		{
			solution[row][col+1]=1;
			if(solve(row, col+1))
				return true;
			solution[row][col+1]=0;
		}
		
		return false;
	}
	
	public static void main(String[] args) 
	{
		int[][] maze = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,1,1}};
		RatMaze r = new RatMaze(maze, 4, 4);
		r.solve();
	}
}
