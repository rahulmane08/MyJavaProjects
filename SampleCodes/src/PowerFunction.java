
public class PowerFunction {
	static double power(double no, int pow)
	{
		if(pow==0)
			return 1;
		if(pow==1)
			return no;
		
		if(pow%2==0)
			return power(no*no, pow/2);
		else
			return no*power(no*no, pow/2);
	}
	
	public static void main(String[] args) {
		System.out.println(power(5.5, 3));
	}
}
