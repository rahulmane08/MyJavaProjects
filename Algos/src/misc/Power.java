package misc;

public class Power {
	public static long power(int num, int power)
	{
		if(power==0)
			return 1;
		if(power==1)
			return num;
		
		if(power%2==0)
			return power(num^2,power/2);
		else
			return num*power(num^2,power/2);
	}
	
	public static void main(String[] args) {
		System.out.println(power(2, 2));
	}
}
