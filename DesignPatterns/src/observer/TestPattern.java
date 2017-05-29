package observer;

public class TestPattern {
	public static void main(String[] args) {
		WeatherData sender = new WeatherData();
		CurrentConditions cond1 = new CurrentConditions("observer1",sender);
		CurrentConditions cond2 = new CurrentConditions("observer2",sender);
		
		for(int i=1;i<5;i++)
			sender.setWeatherConditions(10*i, 15*i);
		
	}
}
