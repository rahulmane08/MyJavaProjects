package interruption;

public class SelfInterrupt {
	public static void main(String[] args) {
		Thread.currentThread().interrupt();
		while(!Thread.currentThread().isInterrupted())
			System.out.println("waiting for interrupt");
		System.out.println("exiting");
	}
}
