package deryl.jibin.clyde.ovenfresh.task;


import deryl.jibin.clyde.ovenfresh.R;
import deryl.jibin.clyde.ovenfresh.ErrorActivity;
import deryl.jibin.clyde.ovenfresh.MYBookingActivity;
import deryl.jibin.clyde.ovenfresh.MainActivity;
import deryl.jibin.clyde.ovenfresh.RegisterationActivity;
import deryl.jibin.clyde.ovenfresh.SplashActivity;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import Utils.JsonHelper;
import Utils.ServerInteraction;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class InitialDataTask extends AsyncTask<Void, Integer, Void> 
{
	
	private Context mContext;
	String mobileNo;
	boolean isTaskSuccessfull = true;
	
	
	public InitialDataTask(Context mContext)
	{
		this.mContext = mContext ;
	}
	
	@Override
	protected void onPreExecute() 
	{
	   // update the UI immediately after the task is executed
	   super.onPreExecute();
	   if(Utility.sharedPrefence == null)
		{
			 Utility.sharedPrefence = mContext.getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
		}
        mobileNo = Utility.sharedPrefence.getString(Constants.MobileNumberTag, "");
	 }

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		
		// GET CATEGORY DATA
		try
		{
			String data = ServerInteraction.getJson(Constants.getCategoryList);
		
			if(data != null)
			{
				JsonHelper.createCategoryList(data);
			}
			else
			{
				Log.d("DERYL", ">>> NO CATEGORY FOUND >>>>>>>>");
				isTaskSuccessfull = false;
			}
		
			// GET PRODUCT DATA
			data = ServerInteraction.getJson(Constants.getProductList);
			if(data != null)
			{
				JsonHelper.createProductList(data);
			}
			else
			{
				Log.d("DERYL", ">>> NO PRODUCT FOUND >>>>>>>>");
				isTaskSuccessfull = false;
			}
		
			// GET BOOKING DATA
			Log.d("DERYL", "mobileNo >>>>>>>>" + mobileNo);
			if(!mobileNo.trim().equals("") && mobileNo.length() == 10)
			{
				data = ServerInteraction.getJson(Constants.getBookingData+mobileNo);
				if(data != null)
				{
					JsonHelper.createMyBookingList(data);
				}
				else
				{
					Log.d("DERYL", ">>>> NO BOOKING FOUND >>>>>");
					isTaskSuccessfull = false;
				}
			}
			return null;
		}
		catch (Exception e) {
			isTaskSuccessfull = false;	
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Void result) 
	{
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		((SplashActivity)mContext).finish();
		if(isTaskSuccessfull)
		{
			  if(Utility.sharedPrefence == null)
				{
					Utility.sharedPrefence = mContext.getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
			    }
			    if (Utility.sharedPrefence.contains(Constants.REGIDConstant))
			    {
			    	Constants.isRegActivity = false;
			    	Log.d("DERYL", "STORED REGID " + Utility.sharedPrefence.getString(Constants.REGIDConstant, ""));
			    	Intent intent = new Intent(mContext, MainActivity.class);
					mContext.startActivity(intent);
			    }
			    else
			    {
			    	Constants.isRegActivity = true;
		    		Intent intent = new Intent(mContext, RegisterationActivity.class);
		    		mContext.startActivity(intent);
			    }
		}
		else
		{
			Intent intent = new Intent(mContext, ErrorActivity.class);
			mContext.startActivity(intent);
		}
	}
}
