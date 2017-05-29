package decorator;

public class Choco extends Condiment {

	private Beverage beverage;
	
	
	public Choco(Beverage beverage) {
		super();
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription()+" with Choco";
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 3+beverage.getCost();
	}

}
