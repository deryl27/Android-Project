package deryl.jibin.clyde.ovenfresh.task;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


import deryl.jibin.clyde.ovenfresh.ErrorActivity;
import deryl.jibin.clyde.ovenfresh.MYBookingActivity;
import deryl.jibin.clyde.ovenfresh.MainActivity;
import deryl.jibin.clyde.ovenfresh.SplashActivity;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.ServerInteraction;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

public class BookingRefreshTask extends AsyncTask<Void, Integer, Void> 
{
	
	private Activity mContext;
	String mobileNo;
	boolean isTaskSuccessfull = true;
	ProgressDialog pd;
	
	public BookingRefreshTask(Activity mContext)
	{
		this.mContext = mContext ;
	}
	
	@Override
	protected void onPreExecute() 
	{
	   // update the UI immediately after the task is executed
	   super.onPreExecute();
	    pd = new ProgressDialog(mContext);
		pd.setTitle(Constants.RefershingTitle);
		pd.setMessage(Constants.progressRefreshMessage);
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
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
		
			// GET BOOKING DATA
			Log.d("DERYL", "mobileNo >>>>>>>>" + mobileNo);
			if(!mobileNo.trim().equals("") && mobileNo.length() == 10)
			{
				String data = ServerInteraction.getJson(Constants.getBookingData+mobileNo);
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
		 if (pd!=null) {
				pd.dismiss();
			}
		if(!isTaskSuccessfull)
		{
			Intent intent = new Intent(mContext, ErrorActivity.class);
			mContext.startActivity(intent);
		}
		else
		{
			((MYBookingActivity) mContext).createList();
		}
	}
	

}