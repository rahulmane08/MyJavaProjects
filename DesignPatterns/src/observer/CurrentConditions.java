package observer;

public class CurrentConditions implements Observer {
	private String name;
	private WeatherData weatherInfo;
	
	
	public CurrentConditions(String name, WeatherData weatherInfo) {
		super();
		this.name = name;
		this.weatherInfo = weatherInfo;
		this.weatherInfo.registerObserver(this);
	}

	

	@Override
	public String toString() {
		return "CurrentConditions [name=" + name + "]";
	}



	@Override
	public void update(float temp, float humidity) {
		// TODO Auto-generated method stub
		System.out.println(this+"current conditions [temp="+temp+"C, humidity="+humidity+"%]");
	}

}
