package Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;

import com.example.weatehrapi.CityWeatherDetailFragment;

public class Constants 
{
	
	public static String  addLocation = "Add Location";
	public static String  about= "About";
	
	public static String Preference = "cityPreference";
	public static String locationTAG = "location";
	
	public static String gpsSetting = "GPS Setting";
	public static String gpsMessage ="GPS is not enabled. Do you want to setting menu ?";
	public static String internetSetting = "Internet Setting";
	public static String internetMessage ="Internet is not enabled. Do you want to setting menu ?";
	public static String setting = "Setting";
	public static String cancel = "Cancel";
	
	public static String errorLocations = "Invalid Location !!!";
	
	public static String APPID = "e96ecad17ddd93f1eda7f84db11a3ee2";
	public static String weatherURL = "http://api.openweathermap.org/data/2.5/forecast?q=";
	public static String weatherEndURL ="&mode=json&APPID="+APPID;
	public static String locationURL = "http://api.openweathermap.org/data/2.5/forecast?lat=";
	
	public static String CITY = "city";
	public static String  LON = "lon";
	public static String  LAT = "lat";
	public static String COUNTRY = "country";
	public static String NAME = "name";
	public static String COORD = "coord";
	public static String LIST = "list";
	
	public static String TEMP = "temp";
	public static String TEMP_MIN = "temp_min";
	public static String TEMP_MAX = "temp_max";
	
	public static String HUMIDITY = "humidity";
	public static String PRESSURE = "pressure";
	public static String SEALEVEL = "sea_level";
	
	public static String  WEATHER = "weather";
	public static String  MAIN = "main";
	
	public static String DESC = "description";
	public static String ICON = "icon";
	
	public static String DTTEXT = "dt_txt";
	
	public static DecimalFormat df = new DecimalFormat("#.#");

	public static boolean isFromLocation = false;
	public static boolean isLocationChanged = false;
	
	public static String DT = "dt";
	
	public static String currentCity = null;
	
	public static CityWeatherDetailFragment mfragment = new CityWeatherDetailFragment();
	
	public static String getData ="Updating Weather Info !!!!!";
	public static String weatherInfo ="Weather Info";
	
	public static FragmentActivity activity = null;
	public static CityPageAdapter adapter = null;
			
}
