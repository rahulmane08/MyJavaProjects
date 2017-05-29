
public final class ImmutableComplexNumber {
	private final double realPart;
	private final double imaginaryPart;
	
	
	public ImmutableComplexNumber(double realPart, double imaginaryPart) {
		super();
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}
	
	public double getRealPart() {
		return realPart;
	}
	public double getImaginaryPart() {
		return imaginaryPart;
	}

	public ImmutableComplexNumber add(ImmutableComplexNumber ic)
	{
		return new ImmutableComplexNumber(realPart+ic.realPart, imaginaryPart+ic.imaginaryPart);
	}
	
	public ImmutableComplexNumber subtract(ImmutableComplexNumber ic)
	{
		return new ImmutableComplexNumber(realPart-ic.realPart, imaginaryPart-ic.imaginaryPart);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return realPart+"+i"+imaginaryPart;
	}
}
class TestComplexNumber
{
	public static void main(String[] args) {
		ImmutableComplexNumber ic = new ImmutableComplexNumber(3, 3);
		System.out.println(ic.add(new ImmutableComplexNumber(2, 10)));
	}
}
