package misc;

class PropagatesInterruption extends Thread
{
	@Override
	public void run() {
		for(int i=0;i<20;i++)
		{
			try {
				Thread.sleep(1000);
				if(i==9)
					Thread.currentThread().interrupt();
			} catch (InterruptedException e) {
				System.out.println("eats interruption");
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			
		}
	}
}
public class PropagatingInterruption {
	public static void main(String[] args) {
		PropagatesInterruption pi = new PropagatesInterruption();
		pi.start();
		while(!pi.isInterrupted());
		System.out.println(pi.getName()+" is interrupted? "+pi.isInterrupted());
	}
}
