package observer.javasupport;

import java.util.Observable;

public class WeatherData extends Observable {
	private float temperature;
	private float humidity;
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
		setChanged();
		notifyObservers();
	}
}
