package decorator;

public class TestPattern {
	public static void main(String[] args) {
		Espresso espresso = new Espresso();
		Mocha mochaEspresso = new Mocha(espresso);
		System.out.println(mochaEspresso);
		
		DarkRoast darkRoast = new DarkRoast();
		Choco doubleChocoDarkRoast = new Choco(new Choco(darkRoast));
		System.out.println(doubleChocoDarkRoast);
	}
}
