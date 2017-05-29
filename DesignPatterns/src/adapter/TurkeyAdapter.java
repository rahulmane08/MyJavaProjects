package adapter;

public class TurkeyAdapter implements Duck{ // the adapter should implement the target interface to get the API of the target interface
	Turkey turkey; // the adapter HAS-A adaptee object to translate the target interface calls to the adaptee API calls.
	
	public TurkeyAdapter(Turkey turkey) {
		// TODO Auto-generated constructor stub
		this.turkey = turkey;
	}
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		turkey.fly();
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		turkey.gobble();
	}
	
}