package decorator;

public class Mocha extends Condiment {

	private Beverage beverage;
	
	
	public Mocha(Beverage beverage) {
		super();
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription()+" with Mocha";
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 2+beverage.getCost();
	}

}
