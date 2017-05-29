package template;

public class Tea extends Beverage {

	public Tea(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("Boil a cup of water for 5 mins and then add tea powder");
	}

}
