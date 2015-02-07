package com.example.weatehrapi;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import entity.LocationList;

import task.GetWeatherTask;



import Utils.CityPageAdapter;
import Utils.Constants;
import Utils.GPSInfo;
import Utils.Utility;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    GPSInfo gps;
    boolean isDialogEnabled =false;
    ViewPager mViewPager;
    static ActionBar actionbar;
    boolean isNetworkDialogEnabled = false;
    
    public void onCreate(Bundle savedInstanceState) {
        
    	Log.d("DERYL", "onCreate >>>> ");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Constants.activity = this;
    	
        actionbar = Utility.setActionBar(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        gps = new GPSInfo(getApplicationContext(),mViewPager, this);
        
        if(gps.isNetworkAvailable())
        {
        	if(!gps.canGetLocation() && Utility.getListOfCity(getApplicationContext()).size() <= 0)
        	{
        		isDialogEnabled = true;
        		gps.showSettingsAlert();
        	}
        }
        else
        {
        	isDialogEnabled = true;
        	gps.openDataSetting();
        }
       
    }
    
    public static void setNavigation(int position)
    {
    	actionbar.setSelectedNavigationItem(position);
    	Constants.currentCity = actionbar.getSelectedTab().getText().toString();
    }
    
    
    
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	  
    	if(isDialogEnabled)
    	{
    		isDialogEnabled = false;
    	}
    	else
    	{
    		if(!gps.isNetworkAvailable() && Utility.getListOfCity(getApplicationContext()).size() <= 0)
    		{
    			finish();
    		}
    		else
    		{
    	    	  if(gps.canGetLocation() && !Constants.isFromLocation 
    	    			  && Utility.getListOfCity(getApplicationContext()).size() <= 0){

    	    		  double latitude = gps.getLatitude();
    	              double longitude = gps.getLongitude();
    	              new GetWeatherTask(this, true, latitude, longitude, mViewPager, getApplicationContext()).execute();
    	    	
    	    	  }else
    	    	  {
    	    		  if(isDialogEnabled)
    	    		  {
    	    			  isDialogEnabled = false;
    	    		  }
    	    		  else
    	    		  {
    	    			  Constants.isFromLocation = false;
    	    			  Constants.isLocationChanged = true;
    	                  if(!Utility.isCityPresent(getApplicationContext()))
    	                  {
    	                	  Intent intent = new Intent(this, AddLocation.class);
    	                	  startActivity(intent);
    	                  }
    	                  else
    	                  {
    	                	 
    	                	  new GetWeatherTask(this, false, 0, 0, mViewPager, getApplicationContext()).execute();
    	                  }
    	    		  }
    	            
    	              
    	         }
    		}
    		
    		
    	}
    	  
    	
    	 
    }
    
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	Utility.savePreferences(getApplicationContext(), Utility.loadSavedPreferences(getApplicationContext()));
    }
    

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
        Constants.currentCity = tab.getText().toString();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!Utility.isCityPresent(getApplicationContext())) {
				Intent intent = new Intent(MainActivity.this, AddLocation.class);
				startActivity(intent);
				return true;
			} else {
				Log.d("DERYL", "BACK CLICK ELSE ");
			}
			
			
		}
		return super.onKeyDown(keyCode, event);
	}
    
    
    
    
    
}