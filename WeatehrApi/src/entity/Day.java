package entity;

import java.util.List;

public class Day {
	
	private String name;
	private String description;
	private double temp;
	private String main;
	private List<WeatherDetails> mWeatherDetails;
	
	public Day()
	{}
	
	
	public Day(String name, String description, double temp, String main,
			List<WeatherDetails> mWeatherDetails) {
		super();
		this.name = name;
		this.description = description;
		this.temp = temp;
		this.main = main;
		this.mWeatherDetails = mWeatherDetails;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}


	public String getMain() {
		return main;
	}


	public void setMain(String main) {
		this.main = main;
	}


	public List<WeatherDetails> getmWeatherDetails() {
		return mWeatherDetails;
	}


	public void setmWeatherDetails(List<WeatherDetails> mWeatherDetails) {
		this.mWeatherDetails = mWeatherDetails;
	}
}
