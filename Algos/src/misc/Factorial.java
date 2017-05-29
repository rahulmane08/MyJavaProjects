package misc;

public class Factorial {
	public static long factorial(int num){
		if(num<0)
			throw new IllegalArgumentException();
		
		if(num>0)
		{
			return num*factorial(num-1);
		}
		else 
			return 1;
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
}
