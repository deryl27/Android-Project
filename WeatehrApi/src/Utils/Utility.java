package Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Text;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatehrapi.About;
import com.example.weatehrapi.AddLocation;
import com.example.weatehrapi.LocationActivity;
import com.example.weatehrapi.MainActivity;
import com.example.weatehrapi.R;

import entity.Day;
import entity.WeatherDetails;


public class Utility {

	public static ActionBar setActionBar(final Activity context)
	{
		ActionBar actionBar = context.getActionBar();
		actionBar.setCustomView(R.layout.custom_title);
		ImageView menuClick = (ImageView) actionBar.getCustomView().findViewById(R.id.menuClick);
		menuClick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Log.d("DERYL", ">> INSIDE THE CLICK >>>");
				
				ImageView image = (ImageView) context.findViewById(R.id.menuClick);
			    
				PopupMenu popupMenu = new PopupMenu(context, image);
				popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
				popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Log.d("DERYL", "menu get groupid "+ item.getTitle());
						if(item.getTitle().equals(Constants.addLocation))
						{
							Intent intent = new Intent(context, LocationActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
							context.startActivity(intent);
						}
						else if(item.getTitle().equals(Constants.about))
						{
							Intent intent = new Intent(context, About.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
							context.startActivity(intent);
						}
						return true;
					}
				});
				popupMenu.show();
				
				
				
				
			}
		});
		actionBar.setIcon(android.R.color.transparent);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
				| ActionBar.DISPLAY_SHOW_HOME);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setLogo(new ColorDrawable(Color.TRANSPARENT));
		return actionBar;
	}
	
	
	
	public static ActionBar setLocationActionBar(final Activity context)
	{
		ActionBar actionBar = context.getActionBar();
		actionBar.setCustomView(R.layout.custom_city_title);
		ImageView backImage = (ImageView) actionBar.getCustomView().findViewById(R.id.backButton);
		backImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Log.d("DERYL", ">> INSIDE THE CLICK >>>");
				Intent intent = new Intent(context,MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
				
		});
		ImageView addLocation = (ImageView) actionBar.getCustomView().findViewById(R.id.addLocation);
		addLocation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("DERYL", ">>  OPEN THE LOCATION");
				Intent intent = new  Intent(context , AddLocation.class);
				context.startActivity(intent);
			}
		});
		actionBar.setIcon(android.R.color.transparent);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM| ActionBar.DISPLAY_SHOW_HOME);
		actionBar.setHomeButtonEnabled(false);
		return actionBar;
	}
	
	
	public static Set<String> loadSavedPreferences(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preference, Context.MODE_PRIVATE);
		return sharedPreferences.getStringSet(Constants.locationTAG, new HashSet<String>());
	}

	public static void savePreferences(Context context, Set<String> location) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preference, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.putStringSet(Constants.locationTAG, location);
		editor.commit();
	}
	
	public static List<String> getListOfCity(Context mContext)
	{
		Set<String> city = Utility.loadSavedPreferences(mContext);
		List<String> allCity = new ArrayList<String>();
		allCity.addAll(city);
		
		return allCity;
	}
	
	public static String getFirstElement(Context mContext)
	{
		Set<String> city = Utility.loadSavedPreferences(mContext);
		List<String> allCity = new ArrayList<String>();
		allCity.addAll(city);
		
		Log.d("DERYL", ">>>>>> getFirst Element >>>> "+ allCity.get(0).toString());
		return allCity.get(0).toString();
		
	}
	 

	
	 public static double convertKelvintoF(double k)
	 {
		 return (k - 273.15)*1.80 + 32.0;
	 }
	
	
	public static boolean isCityPresent(Context context)
	{
		  List<String> city = new ArrayList<>();
			city.addAll(Utility.loadSavedPreferences(context));
			Log.d("DERYL", "UTILITY: city size"+ city.size());
		  return	city.size() <= 0?false:true ;	
	}
	
	
	public static boolean isNewDay(String day)
	{
		return day.equals("00");
	}
	
	
	public static void setDailyHour(WeatherDetails wd, View root)
	{
		String hour = wd.getDateText().split(" ")[1].split(":")[0];
		ImageView mimageView;
		switch (hour) {
		case "00":
			mimageView = (ImageView) root.findViewById(R.id.hour0);
			setImageView(mimageView, wd.getMain());
			break;
		case "03":
			mimageView = (ImageView) root.findViewById(R.id.hour1);
			setImageView(mimageView, wd.getMain());
			break;
		case "06":
			mimageView = (ImageView) root.findViewById(R.id.hour2);
			setImageView(mimageView, wd.getMain());
			break;
		case "09":
			mimageView = (ImageView) root.findViewById(R.id.hour3);
			setImageView(mimageView, wd.getMain());
			break;
		case "12":
			mimageView = (ImageView) root.findViewById(R.id.hour4);
			setImageView(mimageView, wd.getMain());
			break;
		case "15":
			mimageView = (ImageView) root.findViewById(R.id.hour5);
			setImageView(mimageView, wd.getMain());
			break;
		case "18":
			mimageView = (ImageView) root.findViewById(R.id.hour6);
			setImageView(mimageView, wd.getMain());
			break;
		case "21":
			mimageView = (ImageView) root.findViewById(R.id.hour7);
			setImageView(mimageView, wd.getMain());
			break;	
		default:
			break;
		}
	}
	
	public static void setImageView(ImageView img, String desc)
	{
		switch(desc)
		{
			case "Clear": 
				img.setImageResource(R.drawable.sunmicro);
				break;
			case "Clouds":
				img.setImageResource(R.drawable.cloudmicro);
				break;
			case "Snow" :
				img.setImageResource(R.drawable.snowmicro);
		        break;
			case "Rain" :
				img.setImageResource(R.drawable.rainmicro);
				break;
			default : 
				img.setImageResource(R.drawable.sunmicro);
				break;
		}
	}
	
	public static void setFooterDetails(int index, View root, Day d)
	{
		TextView day = null , minmax = null , main = null;
		ImageView img1 = null;
		
		
		switch (index) 
		{
		case 0:
			day = (TextView) root.findViewById(R.id.day1);
			img1 = (ImageView) root.findViewById(R.id.day1_img);
			minmax = (TextView) root.findViewById(R.id.day1_minmax);
			main = (TextView) root.findViewById(R.id.day1_main);
			
			break;
		
		case 1:
			day = (TextView) root.findViewById(R.id.day2);
			img1 = (ImageView) root.findViewById(R.id.day2_img);
			minmax = (TextView) root.findViewById(R.id.day2_minmax);
			main = (TextView) root.findViewById(R.id.day2_main);
			
			break;
	    
		case 2:
			day = (TextView) root.findViewById(R.id.day3);
			img1 = (ImageView) root.findViewById(R.id.day3_img);
			minmax = (TextView) root.findViewById(R.id.day3_minmax);
			main = (TextView) root.findViewById(R.id.day3_main);
			break;
		
		case 3:
			day = (TextView) root.findViewById(R.id.day4);
			img1 = (ImageView) root.findViewById(R.id.day4_img);
			minmax = (TextView) root.findViewById(R.id.day4_minmax);
			main = (TextView) root.findViewById(R.id.day4_main);
			break;
			
		case 4:
			day = (TextView) root.findViewById(R.id.day5);
			img1 = (ImageView) root.findViewById(R.id.day5_img);
			minmax = (TextView) root.findViewById(R.id.day5_minmax);
			main = (TextView) root.findViewById(R.id.day5_main);
			break;
			
		default:
			break;
		}
		if(day != null && img1 != null && minmax != null && main != null)
		{
			day.setText(d.getName());
			setImageView(img1, d.getMain());
			minmax.setText(Constants.df.format(d.getTemp()));
			main.setText(d.getMain());
		}
		
	}
	
	
	

}
