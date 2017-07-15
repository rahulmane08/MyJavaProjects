package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountIsland 
{
	private final int[][] arr, visited;
	private final int M;
	private final int N;
	private final int[] xOptions = {-1,-1,-1,0, 0, 1,1,1};
	private final int[] yOptions = {-1, 0, 1,1,-1,-1,0,1};
	
	public CountIsland(int[][] arr, int m, int n) {
		super();
		this.arr = arr;
		M = m;
		N = n;
		visited = new int[M][N];
		for(int i=0;i<M;i++)
			for(int j=0;j<N;j++)
				visited[i][j]=-1;
	}
	
	private boolean isSafe(int i,int j)
	{
		if(i<0 || j <0 || i>=M || j>=N)
			return false;
		if(visited[i][j]==1 || arr[i][j]==0)
			return false;		
		return true;
	}
	public List<Integer> countIslands()
	{
		List<Integer> islands = new ArrayList<>();
		for(int x=0;x<M;x++)
			for(int y=0;y<N;y++)
				if(isSafe(x, y))
				{
					islands.add(dfs(x, y));
				}	
		return islands;
	}
	
	private int dfs(int x, int y)
	{
		int islandCount = 1;
		for(int i=0;i<8;i++)
		{
			int xIndex = xOptions[i]+x;
			int yIndex = yOptions[i]+y;
			if(isSafe(xIndex,yIndex))
			{
				++islandCount;
				visited[xIndex][yIndex]=1;
			}
		}
		return islandCount;
	}
	
	public static void main(String[] args) {
		int[][]arr = {	{1, 1, 0, 0, 0},
		                {0, 1, 0, 0, 1},
		                {1, 0, 0, 1, 1},
		                {0, 0, 0, 0, 0},
		                {1, 0, 1, 0, 1}
		             };
		List<Integer> islands = new CountIsland(arr, 5, 5).countIslands();
		System.out.println("Total islands = "+islands.size()+" , maxIsland = "+Collections.max(islands));
                
	}
}
