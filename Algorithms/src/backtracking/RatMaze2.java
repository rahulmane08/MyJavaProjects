package backtracking;

import java.util.ArrayList;
import java.util.List;

public class RatMaze2 {
	final private int[][] maze;
	private int[][] solution;
	final int row, col;
	public RatMaze2(int[][] maze,int row, int col) {
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
	
	private boolean solve(int i, int j) {
		if(i==row && j==col)
			return true;
		
		for(Index curr: getOptions(i, j))
		{
			if(isSafe(curr.getX(), curr.getY()))
			{
				solution[curr.getX()][curr.getY()]=1;
				if(solve(curr.getX()+1, curr.getY()) || solve(curr.getX(), curr.getY()+1))
					return true;
				solution[curr.getX()][curr.getY()]=0;
			}
		}
		return false;
	}

	private boolean isSafe(int row , int col)
	{
		return (row>=0 && row<this.row) && (col>=0 && col<this.col) && maze[row][col]==1;
	}
	
	class Index
	{
		final int x;
		final int y;
		public Index(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}		
	}
	
	private List<Index> getOptions(int x, int y)
	{
		List<Index> indexes = new ArrayList<>();
		indexes.add(new Index(x+1, y));
		indexes.add(new Index(x,y+1));
		return indexes;
	}
	public static void main(String[] args) 
	{
		int[][] maze = {{1,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,1,1}};
		RatMaze r = new RatMaze(maze, 4, 4);
		r.solve();
	}
}
