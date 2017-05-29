package decorator;

abstract public class Beverage {
	private String description="unknown beverage";
	public String getDescription(){
		return description;
	}
	abstract public int getCost();
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+getDescription()+": "+getCost()+" $]";
	}
}
