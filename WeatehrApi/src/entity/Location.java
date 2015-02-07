package entity;

import java.util.ArrayList;
import java.util.List;

public class Location {
	
	private String name;
	private double lon;
	private double lat;
	private String country;
	
	
	List<Day> mDays;
	
	public Location()
	{
		mDays = new ArrayList<Day>();
	}

	public Location(String name, double lon, double lat, String country,
			List<Day> mDays) {
		super();
		this.name = name;
		this.lon = lon;
		this.lat = lat;
		this.country = country;
		this.mDays = mDays;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Day> getDays() {
		return mDays;
	}

	public void setDays(List<Day> mDays) {
		this.mDays = mDays;
	}
	

}
