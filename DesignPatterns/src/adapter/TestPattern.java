package adapter;

public class TestPattern {
	public static void main(String[] args) {
		//client gets the duck behaviour
		Duck duck = new MallardDuck();
		duck.fly();
		duck.quack();
		
		//client gets the turkey behaviour
		Turkey turkey = new WildTurkey();
		turkey.fly();
		turkey.gobble();
		
		//client gets the duck out a turkey
		TurkeyAdapter turkeyAdapter = new TurkeyAdapter(turkey);
		turkeyAdapter.fly();
		turkeyAdapter.quack(); // client gets a quack out of a turkey :)
	}
}
