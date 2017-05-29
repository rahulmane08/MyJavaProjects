package strategy;

public class MallardDuck extends Duck {
	public MallardDuck(Flyable flier, Quackable quacker) {
		super(flier, quacker);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Mallard Duck";
	}
}
