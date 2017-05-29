package concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class Element implements Delayed
{
	private long expiryTime = 0;
	

	private String elementName;
	

	public String getElementName() {
		return elementName;
	}

	public Element(long expiryTime, String elementName) {
		super();
		this.expiryTime = expiryTime+System.currentTimeMillis();
		this.elementName = elementName;
	}

	@Override
	public int compareTo(Delayed element) {
		// TODO Auto-generated method stub
		TimeUnit unit = TimeUnit.MILLISECONDS;
		long delay1 = this.getDelay(unit);
		long delay2 = element.getDelay(unit);
		if(delay1>delay2) return 1;
		else if(delay1<delay2) return -1;
		else return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long timeToExpire = unit.convert(expiryTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		return timeToExpire;
	}
	
}
public class DelayQueueTest {
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Delayed> dQ = new DelayQueue<Delayed>();
		dQ.add(new Element(2000, "elem1"));
		dQ.add(new Element(15000, "elem2"));
		dQ.add(new Element(12000, "elem3"));
		dQ.add(new Element(9000, "elem4"));
		dQ.add(new Element(6000, "elem5"));
		
		while(!dQ.isEmpty())
		{
			Element elem = (Element) dQ.take();
			System.out.println("got the element = "+elem.getElementName());
		}
		
	}
}
