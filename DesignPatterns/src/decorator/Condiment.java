package decorator;

public abstract class Condiment extends Beverage {

	protected Beverage beverage;
	
	public Condiment(Beverage beverage) {
		super();
		this.beverage = beverage;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public abstract String getDescription();
	
}
