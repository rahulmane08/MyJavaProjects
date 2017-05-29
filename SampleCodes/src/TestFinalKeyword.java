import java.util.ArrayList;

interface Bounceable
{
	final static int BOUNCE_HEIGHT = 100;
}
public class TestFinalKeyword {
	public static void main(String[] args) {
		final ArrayList<String> list = new ArrayList<String>();
		list = new ArrayList<String>();// gives compile time error
		list.add("Rahul");
		list.add("mane");
	}
}
