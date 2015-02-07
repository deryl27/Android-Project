package com.example.weatehrapi;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.Day;
import entity.Location;
import entity.LocationList;
import entity.WeatherDetails;
import Utils.Constants;
import Utils.Utility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CityWeatherDetailFragment extends Fragment implements OnPageChangeListener {

    public static final String ARG_SECTION_NUMBER = "section_number";

    TextView mcityName , currentDate, description, minmaxTemp, temp, latlon;
    TextView pressure, sealevel, humidity;
    ImageView weatherImage;
    LinearLayout mainLayout;
    Format formatter = new SimpleDateFormat("EEE dd MMM");
    static View rootView;
    
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
    	Log.d("DERYL", ">>>> ON CREATE VIEW >>>>>>");
    	rootView = inflater.inflate(R.layout.fragment_city_detail, container, false);
        updateLayout();
        return rootView;
    }
    
    
    
    
    
    public void updateLayout()
    {
    	
        Bundle args = getArguments();
      
        mcityName = (TextView) rootView.findViewById(R.id.cityName);
        currentDate = (TextView) rootView.findViewById(R.id.currentDate);
        description = (TextView) rootView.findViewById(R.id.description);
        minmaxTemp = (TextView) rootView.findViewById(R.id.minmaxTemp);
        temp = (TextView) rootView.findViewById(R.id.temp);
        latlon = (TextView) rootView.findViewById(R.id.latlon);
        mainLayout = (LinearLayout) rootView.findViewById(R.id.mainLayout);
        weatherImage = (ImageView) rootView.findViewById(R.id.weatherImage);
        sealevel = (TextView) rootView.findViewById(R.id.seaLevel);
        pressure = (TextView) rootView.findViewById(R.id.pressure);
        humidity = (TextView) rootView.findViewById(R.id.humidity);
        
        if(LocationList.getInstance().getmLocationList() != null)
        {
        	
        	List<Location> mLocation = LocationList.getInstance().getmLocationList();
        	for(Location loc : mLocation) 
        	{
        		if(loc.getName().equals(Constants.currentCity))
        		{
        			mcityName.setText(loc.getName()+","+loc.getCountry());
        			
        			long currentTime = System.currentTimeMillis();
        			Day currentDay = null;
        			outerloop:
        			for(Day d: loc.getDays())
        			{
        				for(WeatherDetails wd : d.getmWeatherDetails())
        				{
        					if(currentTime <= wd.getCurrenttime())
        					{
        						currentDay = d;
        						currentDate.setText(formatter.format(new Date(wd.getCurrenttime())));
        						description.setText(wd.getDescription());
        						setWeatherImage(wd.getMain());
        						minmaxTemp.setText(Constants.df.format(wd.getTempMin())+"/"+Constants.df.format(wd.getTempMax())+"F");
        						temp.setText(Constants.df.format(wd.getTemp())+"F");
        						latlon.setText(Constants.df.format(loc.getLat())+"/" + Constants.df.format(loc.getLon()));
        						sealevel.setText(Constants.df.format(wd.getSealevel()));
        						humidity.setText(Constants.df.format(wd.getHumidity()));
        						pressure.setText(Constants.df.format(wd.getPressure()));
        						break outerloop;
        					}
        				}
        			}
        			for (WeatherDetails wd : currentDay.getmWeatherDetails()) 
        			{
        				Utility.setDailyHour(wd, rootView);
					}
        			int counter = 0;
        			for (Day d : loc.getDays()) 
        			{
        				Utility.setFooterDetails(counter, rootView, d);
        				counter++;
					}
        			break;
        		}
				
			}
        	
        }
    }
    
    public void setWeatherImage(String weather)
    {
    	switch (weather) {
			case "Clear": 
				mainLayout.setBackgroundResource(R.drawable.backsunny);
				weatherImage.setImageResource(R.drawable.sun);
				break;
			case "Clouds":
				mainLayout.setBackgroundResource(R.drawable.backcloudy);
				weatherImage.setImageResource(R.drawable.cloud);
				break;
			case "Snow" :
				mainLayout.setBackgroundResource(R.drawable.backsnow);
				weatherImage.setImageResource(R.drawable.snow);
		        break;
			case "Rain" :
				mainLayout.setBackgroundResource(R.drawable.backrainy);
				weatherImage.setImageResource(R.drawable.rain);
				break;
			default : 
				mainLayout.setBackgroundResource(R.drawable.backsunny);
				weatherImage.setImageResource(R.drawable.sun);
				break;
		}
    }
    
    
    
    @Override
    public void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	
    	
    }


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		MainActivity.setNavigation(position);
		Constants.adapter.notifyDataSetChanged();
		//updateLayout();
	}
	

    
    
    
    
    
    
}
