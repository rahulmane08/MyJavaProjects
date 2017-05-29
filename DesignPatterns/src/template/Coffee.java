package template;


public class Coffee extends Beverage {

	public Coffee(String description) {
		super(description);		
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("Brew coffee beans in a hot water jug");
	}
	

}
