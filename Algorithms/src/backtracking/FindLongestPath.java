package backtracking;

import java.util.LinkedHashSet;
import java.util.Set;

public class FindLongestPath {
	private final int[][] arr, visited;
	private final int M;
	private final int N;
	private final int[] xOptions = {-1,0, 0,1};
	private final int[] yOptions = { 0,1,-1,0};	
	private Set<Point> maxPath = new LinkedHashSet<>();
	
	class Point
	{
		int x,y;
		
		

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private FindLongestPath getOuterType() {
			return FindLongestPath.this;
		}
		
	}
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
		trace(startX, startY, endX, endY, 0, new LinkedHashSet<>());
		if(maxPath.size() == 0)
			System.out.println("No Path found from ("+startX+","+startY+") and ("+endX+","+endY+")");
		else
		{
			System.out.println("Path found from ("+startX+","+startY+") and ("+endX+","+endY+") of max length = "+maxPath.size());
			maxPath.forEach(e->{System.out.println(e);});
		}
			
	}
	
	private void trace(int startX,int startY, int endX, int endY, int currCount, LinkedHashSet<Point> currPath)
	{
		visited[startX][startY]=1;
		Point p = new Point(startX, startY);
		currPath.add(p);
		if(startX==endX && startY == endY)
		{
			if(currPath.size()>maxPath.size())
				maxPath = new LinkedHashSet<>(currPath);			
			
		}		
		for(int i=0;i<4;i++)
		{
			int x = xOptions[i]+startX;
			int y = yOptions[i]+startY;
			if(isSafe(x, y))
			{				
				trace(x, y, endX, endY, ++currCount,currPath);				
			}
		}		
		currPath.remove(p);
		visited[startX][startY]=-1;
	}
	
	public static void main(String[] args) {
		int[][] arr = {
						{1,1,1,1,1},
						{1,0,1,0,1},
						{1,1,1,0,1},
						{1,1,1,0,1},
						{1,1,1,1,1}
					  };
		new FindLongestPath(arr, 5, 5).solve(1, 4, 4, 4);
	}
}
