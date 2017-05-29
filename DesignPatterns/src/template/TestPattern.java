package template;

public class TestPattern {
	public static void main(String[] args) {
		Tea tea = new Tea("Green Tea");
		Coffee coffee = new Coffee("Black Coffee");
		Beverage[] beverages = {tea,coffee};
		for(Beverage b: beverages)
		{
			b.boilWater();
			b.prepareRecipe();
			b.prepare();
			b.pourInCup();
		}
	}
}
