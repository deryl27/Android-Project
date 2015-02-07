package task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.example.weatehrapi.ErrorActivity;
import com.example.weatehrapi.MainActivity;


import entity.Location;
import entity.LocationList;

import Utils.CityPageAdapter;
import Utils.Constants;
import Utils.JsonHelper;
import Utils.ServerInteraction;
import Utils.Utility;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

public class GetWeatherTask extends AsyncTask<Void, Integer, Void> 
{
	
	private FragmentActivity mContext;
	boolean isTaskSuccessfull = true;
	boolean isLocationEnabled = false;
	private double latitude, longitude;
	CityPageAdapter mCityPagerAdapter;
	ViewPager mViewPager;
	Context applicationContext;
	ProgressDialog pd;
	
	public GetWeatherTask(FragmentActivity mContext, boolean isLocationEnabled, double latitude, double longitude, 
			              ViewPager mViewPager, Context applicationContext)
	{
		this.mContext = mContext ;
		this.isLocationEnabled = isLocationEnabled;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mViewPager = mViewPager;
		this.applicationContext = applicationContext;
	}
	
	@Override
	protected void onPreExecute() 
	{
	   // update the UI immediately after the task is executed
	   super.onPreExecute();
	   pd = new ProgressDialog(mContext);
	 		pd.setTitle(Constants.weatherInfo);
	 		pd.setMessage(Constants.getData);
	 		pd.setCancelable(false);
	 		pd.setIndeterminate(true);
	 		pd.show();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		List<Location> mLocation;
		try
		{
			Log.d("DERYL", ">>> LOCATION ENABLED HELLo " + isLocationEnabled);
			if(isLocationEnabled)
			{
				Log.d("DERYL", ">>> LOCATION ENABLED ");
				String data = ServerInteraction.getJson(Constants.locationURL+latitude+"&lon="+longitude);
				Log.d("DERYL", "data >>>>> " + data);
				Location loct = null;
				if(data != null)
				{
				     loct =  JsonHelper.getWeatherDetails(data , null);
				}
				if(LocationList.getInstance().getmLocationList()!= null)
				{
					LocationList.getInstance().getmLocationList().add(loct);
				}
				else
				{
					mLocation = new ArrayList<Location>();
					mLocation.add(loct);
					LocationList.getInstance().setmLocationList(mLocation);
					Set<String> location = Utility.loadSavedPreferences(applicationContext);
					location.add(loct.getName());
					Utility.savePreferences(applicationContext, location);
				}
			}
			else
			{
				Log.d("DERYL", ">>>>>>> LocationList.getInstance().getmLocationList()" + LocationList.getInstance().getmLocationList());
				if(LocationList.getInstance().getmLocationList() != null)
					LocationList.getInstance().getmLocationList().clear();
			    mLocation = new ArrayList<Location>();
			    List<String> allCity = Utility.getListOfCity(applicationContext);
			    boolean isFirstElement = true;
				for (String loc : allCity) 
				{
					Log.d("DERYL", ">>> loc" + loc);
					String url = Constants.weatherURL+loc +Constants.weatherEndURL;
					StringBuffer  actualurl = null;
					if(url.contains(" "))
						actualurl =  new StringBuffer(url.split(" ")[0]+"%20"+url.split(" ")[1]);
					else
						actualurl = new StringBuffer(url);
					Log.d("DERYL", "Actual URL >>>>>> ");
					String data = ServerInteraction.getJson(actualurl.toString());
				    Log.d("DERYL", "data >>>>> " + data);
				    Location loct = null;
				    if(isFirstElement)
				    {
				    	Constants.currentCity = loc;
				    	isFirstElement = false;
				    }
					if(data != null)
					{
						 Log.d("DERYL", "JSON DATA ");
					     loct =  JsonHelper.getWeatherDetails(data , loc);
					     
					  mLocation.add(loct);
					}
					LocationList.getInstance().setmLocationList(mLocation);
					
				}
				LocationList.getInstance().setmLocationList(mLocation);
			Log.d("DERYL", ">>>> SIZE OF LOCATION LIST"+ LocationList.getInstance() + LocationList.getInstance().getmLocationList().size());
			}
			
			
			return null;
		}
		catch (Exception e) {
			Log.d("DERYL", ">>> ERORR" + e.getLocalizedMessage());
			isTaskSuccessfull = false;	
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Void result) 
	{
		super.onPostExecute(result);
		 if (pd!=null) {
				pd.dismiss();
			}
		if(isTaskSuccessfull)
		{
			Log.d("DERYL", ">>> COMPLETE");
			
			List<String> city = new ArrayList<String>();
			city = Utility.getListOfCity(applicationContext);
			
			for(int i = 0 ; i < city.size(); i++)
			{
				Log.d("DERYL",  ">> ON POST EXECUTE " + city.get(i));
			}
			
			Log.d("DERYL", "city size" + city.size());
	         mCityPagerAdapter = new CityPageAdapter (mContext.getSupportFragmentManager(), city);

	         mViewPager.setAdapter(mCityPagerAdapter);
	         Constants.adapter = mCityPagerAdapter;
	         final ActionBar actionBar = Utility.setActionBar(mContext);
	         actionBar.removeAllTabs();
	         mViewPager.setOnPageChangeListener(Constants.mfragment);
	         for (int i = 0; i < mCityPagerAdapter.getCount(); i++) {
	             actionBar.addTab(
	                     actionBar.newTab().setText(mCityPagerAdapter.getPageTitle(i)).setTabListener((MainActivity)mContext));
	         }
	        
	         
	         if(Constants.isLocationChanged)
	         {
	        	 Constants.isLocationChanged = false;
	         }
		}
		else
		{
			Intent intent = new Intent(mContext, ErrorActivity.class);
			mContext.startActivity(intent);
		}
	}
	
	
	

}