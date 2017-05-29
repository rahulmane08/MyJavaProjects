package misc;

public class PascalTriangle {

	static public void pascalTriangle(int[] arr, int levels, int currentLevel)
	{
		print(arr);
		if(levels == currentLevel)
			return;	
		currentLevel++;
		int[] next = new int[currentLevel];
		next[0]=next[next.length-1]=1;
		if(currentLevel>2)
			for(int i=-1,j=i+1,z=0;i<arr.length;i++,j++,z++)
			{
				int x=0;
				if(i>-1)
					x=arr[i];
				int y=0;
				if(j<arr.length)
					y=arr[j];
				next[z]=x+y;
			}
			
		pascalTriangle(next, levels, currentLevel);
	}
	private static void print(int[] arr) {
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	public static void main(String[] args) {
		int levels = 6;
		pascalTriangle(new int[]{1}, levels, 1);
	}
}
