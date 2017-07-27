package backtracking;

public class FindLongestPath {
	private final int[][] arr, visited;
	private final int M;
	private final int N;
	private final int[] xOptions = {-1,0, 0,1};
	private final int[] yOptions = { 0,1,-1,0};
	private int count = Integer.MIN_VALUE;
	
	public FindLongestPath(int[][] arr, int m, int n) {
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
	
	public void solve(int startX,int startY, int endX, int endY)
	{
		trace(startX, startY, endX, endY, 0);
		if(count==Integer.MIN_VALUE)
			System.out.println("No Path found from ("+startX+","+startY+") and ("+endX+","+endY+")");
		else
			System.out.println("Path found from ("+startX+","+startY+") and ("+endX+","+endY+") of max length = "+count);
	}
	
	private void trace(int startX,int startY, int endX, int endY, int currCount)
	{
		visited[startX][startY]=1;
		if(startX==endX && startY == endY)
		{
			count = Math.max(count, currCount);
		}		
		for(int i=0;i<4;i++)
		{
			int x = xOptions[i]+startX;
			int y = yOptions[i]+startY;
			if(isSafe(x, y))
			{				
				trace(x, y, endX, endY, ++currCount);				
			}
		}
		visited[startX][startY]=-1;
	}
	
	public static void main(String[] args) {
		int[][] arr = {
						{1,1,1,1,1},
						{1,0,1,0,1},
						{1,0,1,0,0},
						{1,0,1,0,0},
						{1,1,1,1,1}
					  };
		new FindLongestPath(arr, 5, 5).solve(1, 4, 4, 4);
	}
}
