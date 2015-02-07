package Utils;

import java.util.ArrayList;
import java.util.List;

import task.GetWeatherTask;

import com.example.weatehrapi.AddLocation;
import com.example.weatehrapi.MainActivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class GPSInfo extends Service implements LocationListener {

	private final Context context;
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	ViewPager mViewPager;
	private FragmentActivity fragmentcontext;
	
	
	boolean canRetreiveUserLocation = false;
	
	Location location ;
	double latitude,longitude;
	
	private static final long DISTANCE_FOR_UPDATES = 1000;
	
	private static final long MINIMUM_TIME = 1000 * 60 * 1;
	
	LocationManager locationManager;
	
	public GPSInfo(Context context, ViewPager mViewPager, FragmentActivity fragmentcontext)
	{
		this.context = context;
		this.mViewPager = mViewPager;
		this.fragmentcontext = fragmentcontext;
		getLocation();
	}
	
	public Location getLocation()
	{
		try
		{
			locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
			
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(isGPSEnabled && isNetworkEnabled)
			{
				this.canRetreiveUserLocation = true;
				if(isNetworkEnabled)
				{
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 
							MINIMUM_TIME, DISTANCE_FOR_UPDATES, this);
					
					if(locationManager != null)
					{
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location != null)
						{
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				
			}
			if (isGPSEnabled) 
			{
                 if (location == null) 
                 {
                	 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
 							MINIMUM_TIME, DISTANCE_FOR_UPDATES, this);
                     Log.d("DERYL", ">>>>>>>>>>>>>GPS Enabled");
                     if (locationManager != null) {
                         location = locationManager
                                 .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                         if (location != null) {
                             latitude = location.getLatitude();
                             longitude = location.getLongitude();
                         }
                     }
                 }
             }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return location;
		
	}
	
	public boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) fragmentcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	public void stopUsingGPS()
	{
        if(locationManager != null){
            locationManager.removeUpdates(GPSInfo.this);
        }       
    }
	
	public double getLatitude()
	{
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

	public double getLongitude()
	{
        if(location != null){
            longitude = location.getLongitude();
        }
		return longitude;
	}
	
	public boolean canGetLocation()
	{
	        return this.canRetreiveUserLocation;
	}
	
	 public void showSettingsAlert()
	 {
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragmentcontext);
	        alertDialog.setTitle(Constants.gpsSetting);
	        alertDialog.setMessage(Constants.gpsMessage);
	        alertDialog.setCancelable(false);
	        alertDialog.setPositiveButton(Constants.setting, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int which) {
	                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                fragmentcontext.startActivity(intent);
	            }
	        });
	        alertDialog.setNegativeButton(Constants.cancel, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {

						if (!Utility.isCityPresent(context)) {
							Intent intent = new Intent(fragmentcontext, AddLocation.class);
							fragmentcontext.startActivity(intent);
						} else {
							Log.d("DERYL", "CANCEL CLICK ");
						}
	            dialog.cancel();
	            }
	        });
	        alertDialog.show();
	 }
	 
	 public void openDataSetting()
	 {
		 AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragmentcontext);
	        alertDialog.setTitle(Constants.internetSetting);
	        alertDialog.setMessage(Constants.internetMessage);
	        alertDialog.setCancelable(false);
	        alertDialog.setPositiveButton(Constants.setting, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int which) {
	            	Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
	       		 	fragmentcontext.startActivity(intent);
	            }
	        });
	        alertDialog.setNegativeButton(Constants.cancel, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	((MainActivity) fragmentcontext).finish();
	            dialog.cancel();
	            }
	        });
	        alertDialog.show();
		 
		 
	 }

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location != null)
		{
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			if(!Constants.isLocationChanged)
				new GetWeatherTask(fragmentcontext, true, latitude, longitude, mViewPager, context).execute();
			
		}
	}
	
	

}
