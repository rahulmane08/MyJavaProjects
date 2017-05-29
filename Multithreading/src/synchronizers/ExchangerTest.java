package synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

class DataBuffer
{
	private List<Object> buffer;
	private int totalCapacity;
	public DataBuffer(int size) {
		super();
		buffer = new ArrayList<Object>(size);
		this.totalCapacity = size;
	}
	public synchronized boolean isEmpty()
	{
		return buffer.size()==0;
	}
	public boolean isFull()
	{
		return buffer.size()==this.totalCapacity;
	}
	public synchronized void addToBuffer(Object o){
		if(!isFull())
			addToBuffer(o);
	}
	public synchronized Object getFromBuffer()
	{
		if(!isEmpty())
		{
			Object o = buffer.remove(0);
			return o;
		}
		return null;
	}
}


public class ExchangerTest {
	public Exchanger<DataBuffer> exchanger;
	DataBuffer buffer1 = new DataBuffer(10);
	DataBuffer buffer2 = new DataBuffer(10);
	class BufferProducer implements Runnable
	{
		@Override
		public void run() {
			
			for(int i =0;i<10;i++)
			{
				
			}
		}	
		
	}
}
