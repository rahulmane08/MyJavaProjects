package recursions;

public class TowersOfHanoi 
{
	static void towersOfHanoi(int n, String source, String dest, String aux)
	{
		System.out.println(source+","+aux+","+dest);
		if(n==1)
		{
			System.out.println("moving disk 1 from "+source+" to "+dest);
			return;
		}
			
		towersOfHanoi(n-1, source, aux, dest);
		System.out.println("moving disk "+n+" from peg "+source+" to "+dest);
		towersOfHanoi(n-1, aux, dest, source);
	}
	public static void main(String[] args) {
		towersOfHanoi(3, "A", "C", "B");
		
	}
}
