package entity;

import java.util.List;

public class LocationList {
	
	private List<Location> mLocationList;
	
	private static LocationList instance;
	
	public static LocationList getInstance()
	{
		if(instance == null)
		{
			instance = new LocationList();
		}
		
		return instance;
		
	}
	
	public List<Location> getmLocationList() {
		return mLocationList;
	}
	
	public void setmLocationList(List<Location> mLocationList) {
		this.mLocationList = mLocationList;
	}
	
	
}
