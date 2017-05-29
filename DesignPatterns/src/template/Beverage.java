package template;

public abstract class Beverage {
	
	private String description;
	
	
	public Beverage(String description) {
		super();
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.description;
	}
	public void prepareRecipe()
	{
		System.out.println("preparing a cup of "+this);
	}
	public void boilWater() {
		// TODO Auto-generated method stub
		System.out.println("boiling a cup of water to make "+this);
	}
	
	public void pourInCup() {
		// TODO Auto-generated method stub
		System.out.println("pouring "+this+" into a cup");
	}
	
	abstract public void prepare(); //this method is let for the child methods to implement in their own way
}
