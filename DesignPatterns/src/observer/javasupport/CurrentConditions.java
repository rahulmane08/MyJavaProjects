package observer.javasupport;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditions implements Observer{
	private String name;
	private Observable weatherData;
	
	public CurrentConditions(String name, Observable weatherData) {
		super();
		this.name = name;
		this.weatherData = weatherData;
		weatherData.addObserver(this);
	}
	
	@Override
	public String toString() {
		return "CurrentConditions [name=" + name + "]";
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub		
		WeatherData w = (WeatherData)o;
		float temp = w.getTemperature();
		float humidity = w.getHumidity();
		System.out.println(this+"current conditions [temp="+temp+"C, humidity="+humidity+"%]");
	}

}
