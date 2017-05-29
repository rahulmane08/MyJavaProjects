package observer;

import java.util.ArrayList;

public class WeatherData implements Subject{
	private float temperature;
	private float humidity;
	private ArrayList<Observer> observers;
	
	public WeatherData() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<Observer>();
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public void setWeatherConditions(float temp, float humidity)
	{
		setHumidity(humidity);
		setTemperature(temp);
		notifyObserver();
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int index = observers.indexOf(o);
		observers.remove(index);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(Observer o: observers)
			o.update(temperature, humidity);
	}
	
	
}
