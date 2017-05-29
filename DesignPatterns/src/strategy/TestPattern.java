package strategy;

public class TestPattern {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck(new HighFlier(), new Quack());
		System.out.println(duck);
		duck.setFlier(new FlyWithWings());
		System.out.println(duck);
		duck.setQuacker(new Squeak());
		System.out.println(duck);
	}
}
