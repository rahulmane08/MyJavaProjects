package backtracking;

public class Print2DArray 
{
	public static void print(int[][] arr, int col, int row)
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)			
				System.out.print((j==0?"|":"")+" "+arr[i][j]+" "+(j==col-1?"|":""));
			System.out.println();
		}
	}
}
