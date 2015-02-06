package deryl.jibin.clyde.ovenfresh.task;

import java.lang.annotation.RetentionPolicy;


import deryl.jibin.clyde.ovenfresh.CustomerActivity;
import deryl.jibin.clyde.ovenfresh.ErrorActivity;
import deryl.jibin.clyde.ovenfresh.ReportActivity;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import Utils.JsonHelper;
import Utils.ServerInteraction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

public class SubmitOrderTask extends AsyncTask<Void, Integer, Void>
{
	Context mContext;
	String orderDetails, FirstName, LastName, Email, OrderDate, OrderTime, MobileNo, UserStatus;
	ProgressDialog pd;
	Button submitDetails;
	boolean isTaskSuccesfull = true;
	
	public SubmitOrderTask(Context mContext, String orderDetails,
			String firstName, String lastName, String email, String orderDate,
			String orderTime, String mobileNo, String userStatus, Button submitDetails) {
		super();
		this.mContext = mContext;
		this.orderDetails = orderDetails;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		OrderDate = orderDate;
		OrderTime = orderTime;
		MobileNo = mobileNo;
		this.UserStatus = userStatus;
		this.submitDetails = submitDetails;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		submitDetails.setEnabled(false);
		Constants.custstatus = "";
		pd = new ProgressDialog(mContext);
		pd.setTitle(Constants.progressMessageTitle);
		pd.setMessage(Constants.submitOrderMessage);
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		// FreshOvenApp/webresources/service/getOrderDetails?orderDetails=P01,1,500_P02,2,1100&mobileNo=98333894862&firstName=god01&lastName=good&emailID=dadasdda&userStatus=NOTEXIST&orderDate=2014-07-29&orderTime=19:00:00
		try
		{
			String data = ServerInteraction.getJson(Constants.putOrderData+orderDetails+"&mobileNo="+MobileNo+"&firstName="+FirstName+"&lastName="+LastName+"&emailID="+Email+"&userStatus="+UserStatus+"&orderDate="+OrderDate+"&orderTime="+OrderTime);
			if(data != null)
			{
				JsonHelper.getOrderid(data);
			}
			else
			{
				Log.d("DERYL", ">>> NO DATA POSTED  >>>>>>>>");
				isTaskSuccesfull = false;
			}
			return null;
		}
		catch (Exception e) {
			isTaskSuccesfull = false;
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (pd!=null) {
			pd.dismiss();
			submitDetails.setEnabled(true);
		}
		if(isTaskSuccesfull)
		{
			if(Utility.sharedPrefence == null)
			{
				 Utility.sharedPrefence = mContext.getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
			}
			Editor editor = Utility.sharedPrefence.edit();
     	    editor.putString(Constants.TagFirstName, FirstName);
     	    editor.putString(Constants.TagLastName,LastName);
     	    editor.putString(Constants.TagEmail, Email);
		    editor.commit(); 
			Intent intent = new Intent(mContext, ReportActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			mContext.startActivity(intent);
		}
		else
		{
			Intent intent = new Intent(mContext, ErrorActivity.class);
			mContext.startActivity(intent);
		}
	}
	

}
