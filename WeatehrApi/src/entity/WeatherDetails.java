package entity;

public class WeatherDetails {

	private double temp;
	private double tempMin;
	private double tempMax;
	
	private double pressure;
	private double humidity;
	private double sealevel;
	private String main;
	private String description;
	private String icon;
	private String dateText;
	private long currenttime;
	
	public WeatherDetails()
	{}


	public WeatherDetails(double temp, double tempMin, double tempMax,
			double pressure, double humidity, String main, String description,
			String icon, String dateText) {
		super();
		this.temp = temp;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
		this.main = main;
		this.description = description;
		this.icon = icon;
		this.dateText = dateText;
	}


	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}


	public double getTempMin() {
		return tempMin;
	}


	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}


	public double getTempMax() {
		return tempMax;
	}


	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}


	public double getPressure() {
		return pressure;
	}


	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	


	public double getSealevel() {
		return sealevel;
	}


	public void setSealevel(double sealevel) {
		this.sealevel = sealevel;
	}


	public double getHumidity() {
		return humidity;
	}


	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}


	public String getMain() {
		return main;
	}


	public void setMain(String main) {
		this.main = main;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getDateText() {
		return dateText;
	}


	public void setDateText(String dateText) {
		this.dateText = dateText;
	}
	
	public long getCurrenttime() {
		return currenttime;
	}

	public void setCurrenttime(long currenttime) {
		this.currenttime = currenttime;
	}
	
	
}