package strategy;

public class Duck {
	private String description="Duck";
	Flyable flier;
	Quackable quacker;
	public Duck(Flyable flier, Quackable quacker) {
		super();
		this.flier = flier;
		this.quacker = quacker;
	}
	public Flyable getFlier() {
		return flier;
	}
	public void setFlier(Flyable flier) {
		this.flier = flier;
	}
	public Quackable getQuacker() {
		return quacker;
	}
	public void setQuacker(Quackable quacker) {
		this.quacker = quacker;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDescription()+" "+flier.fly()+" and "+quacker.quack();
	}
}
