package dynamicprogramming;

import utils.Utils;

public class MinCostPath {
	private int[][] arr,cost;
	private int M,N;
	
	
	public MinCostPath(int[][] arr, int m, int n) {
		super();
		this.arr = arr;
		M = m;
		N = n;
		this.cost = new int[M+1][N+1];		
	}	
	
	public void printMinCostPath()
	{
		cost[0][0] = arr[0][0];
		
		for(int i=1;i<M;i++)
			cost[i][0] = cost[i-1][0]+arr[i][0]; 
		
		for(int j=1;j<N;j++)
			cost[0][j] = cost[0][j-1]+arr[0][j];
		
		for (int i = 1; i < M; i++)
            for (int j = 1; j < N; j++)
            	cost[i][j] = Utils.min(cost[i-1][j-1],cost[i-1][j],cost[i][j-1]) + arr[i][j];
		
		System.out.printf("Min cost to reach(%d,%d)=%d",M-1,N-1,cost[M][N]);
	}
	public static void main(String[] args) {
		int arr[][]= {{1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}};
		new MinCostPath(arr, 3, 3).printMinCostPath();
	}
}
