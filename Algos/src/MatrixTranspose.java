
public class MatrixTranspose {
	public static void main(String[] args) {
		int[][] arr = {{1,2},{3,4},{5,6}};
		
		int rows = arr.length;
		int columns = arr[0].length;
		
		int [][] transpose = new int[columns][rows];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				System.out.print(arr[i][j]+" ");
				transpose[j][i] = arr[i][j];
			}
			System.out.println();
		}
		
		System.out.println("transpose");
		rows = transpose.length;
		columns = transpose[0].length;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				System.out.print(transpose[i][j]+" ");
				
			}
			System.out.println();
		}
		
			
	}
}
