package Utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Day;
import entity.Location;
import entity.WeatherDetails;


import android.util.Log;



public class JsonHelper {
	
	public static Location getWeatherDetails(String jsonResponse, String City) throws Exception
	{
		
		Location loc = new Location();
		
		JSONObject location = new JSONObject(jsonResponse);
		JSONObject city = location.getJSONObject(Constants.CITY);
		if(City != null)
		{
			loc.setName(City);
		}
		else
		{
			Constants.currentCity = city.getString(Constants.NAME);
			loc.setName(city.getString(Constants.NAME));
		}
		JSONObject coord = city.getJSONObject(Constants.COORD);
		
		loc.setLon(coord.getDouble(Constants.LON));
		loc.setLat(coord.getDouble(Constants.LAT));
		loc.setCountry(city.getString(Constants.COUNTRY));
		
		
		JSONArray list = location.getJSONArray(Constants.LIST);
		List<WeatherDetails> mWeatherDetails = new ArrayList<WeatherDetails>();
		List<Day> mDay = new ArrayList<Day>();
		Day d  = new Day();
		boolean isFirstDay = true;
		for(int  i = 0; i< list.length(); i++)
		{
			WeatherDetails weather = new WeatherDetails();
			JSONObject json = list.getJSONObject(i);
			JSONObject main = json.getJSONObject(Constants.MAIN);
		
			weather.setCurrenttime(json.getLong(Constants.DT) * 1000L);
			weather.setTemp(Utility.convertKelvintoF(main.getDouble(Constants.TEMP)));
			weather.setTempMin(Utility.convertKelvintoF(main.getDouble(Constants.TEMP_MIN)));
			weather.setTempMax(Utility.convertKelvintoF(main.getDouble(Constants.TEMP_MAX)));
			weather.setPressure(main.getDouble(Constants.PRESSURE));
			weather.setHumidity(main.getDouble(Constants.HUMIDITY));
			weather.setSealevel(main.getDouble(Constants.SEALEVEL));
			JSONArray weatherDetail = json.getJSONArray(Constants.WEATHER);
			weather.setMain(weatherDetail.getJSONObject(0).getString(Constants.MAIN)); 
			
			weather.setDescription(weatherDetail.getJSONObject(0).getString(Constants.DESC));
			weather.setIcon(weatherDetail.getJSONObject(0).getString(Constants.ICON));
			weather.setDateText(json.getString(Constants.DTTEXT));
			
			if(Utility.isNewDay(json.getString(Constants.DTTEXT).split(" ")[1].split(":")[0]))
			{
				Log.d("DERYL", "INSIDE SECOND CONDITION");
				if(mWeatherDetails.size() >= 0)
				{
					Log.d("DERYL", "INSIDE THIRD CONDITION");
					d.setDescription(weather.getDescription());
					d.setMain(weather.getMain());
					d.setTemp(weather.getTemp());
					Format formatter = new SimpleDateFormat("EEE");
					Log.d("DERYL", "d >>"+ weather.getCurrenttime());
					d.setName(formatter.format(new Date(weather.getCurrenttime())));
					d.setmWeatherDetails(mWeatherDetails);
					Log.d("DERYL", "d >>"+ d.getName());
					mDay.add(d);
					mWeatherDetails = new ArrayList<WeatherDetails>();
				}
				d = new Day();
				
			}
			if(mDay.size() <= 0 && isFirstDay)
			{
				isFirstDay = false;
				Log.d("DERYL", "INSIDE FIRST CONDITION");
				d.setDescription(weather.getDescription());
				d.setMain(weather.getMain());
				d.setTemp(weather.getTemp());
				Format formatter = new SimpleDateFormat("EEE");
				d.setName(formatter.format(new Date(weather.getCurrenttime())));
				
			}
			mWeatherDetails.add(weather);
		}
		
		loc.setDays(mDay);
		return loc;
		
	}

}
