package deryl.jibin.clyde.ovenfresh.task;


import deryl.jibin.clyde.ovenfresh.ErrorActivity;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckUserTask extends AsyncTask<String, Integer, Void> 
{
	Context mContext;
	EditText fname, lname, emailid;
	LinearLayout customerinfo;
	TextView message;
	Button checkUserExist;
	ProgressDialog pd;
	boolean isTaskSuccessfull = true;
	
	public CheckUserTask(Context context, EditText fname, EditText lname, EditText emailid, LinearLayout customerinfo, TextView message, Button checkUserExist)
	{
		this.fname = fname;
		this.lname = lname;
		this.emailid = emailid;
		this.customerinfo = customerinfo;
		this.message = message;
		this.mContext = context;
		this.checkUserExist = checkUserExist;
	}

	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		checkUserExist.setEnabled(false);
		pd = new ProgressDialog(mContext);
		pd.setTitle(Constants.progressMessageTitle);
		pd.setMessage(Constants.progressMessage);
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}
	
	
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		try
		{
			String mobileNo = params[0];
			Log.d("DERYL", "mobileNo >>>>>>>" + mobileNo);
			String data = ServerInteraction.getJson(Constants.getCustomerData+mobileNo);
			if(data != null)
			{
				JsonHelper.getCustomerDetails(data);
			}
			else
			{
				Log.d("DERYL", ">>> NO CUSTOMER DATA FOUND >>>>>>>>");
				isTaskSuccessfull = false;
			}
			return null;
		}
		catch (Exception e) {
			// TODO: handle exception
			isTaskSuccessfull = false;
			return null;
		}
	}
	
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		 Log.d("DERYL", "NORMAL FLOW WORKKING !!!!!");
		 if (pd!=null) {
				pd.dismiss();
				checkUserExist.setEnabled(true);
			}
		 if(isTaskSuccessfull)
		 {
		     if(Constants.custstatus.equals(Constants.userExist))
				{
		    	    Log.d("DERYL"," USER EXIST AFTER NEW FLOW");
					fname.setText(Constants.custFName);
					lname.setText(Constants.custLName);
					emailid.setText(Constants.custemail);
					message.setText(Constants.existingUser);
					
					if(Utility.sharedPrefence == null)
					{
						 Utility.sharedPrefence = mContext.getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
					}
					Editor editor = Utility.sharedPrefence.edit();
		     	    editor.putString(Constants.TagFirstName, Constants.custFName);
		     	    editor.putString(Constants.TagLastName,Constants.custLName);
		     	    editor.putString(Constants.TagEmail, Constants.custemail);
				    editor.commit(); 
					
					Constants.custFName="";
					Constants.custLName="";
					//Constants.custstatus="";
					Constants.custemail="";
					
				}
				else if(Constants.custstatus.equals(Constants.userNotExist))
				{
					Log.d("DERYL"," USER EXIST NOT  AFTER NEW FLOW");
					message.setText(Constants.newUser);
				}
				message.setVisibility(View.VISIBLE);
				customerinfo.setVisibility(View.VISIBLE);
				
		 }
		 else
		 {
			 Intent intent = new Intent(mContext, ErrorActivity.class);
			 mContext.startActivity(intent);
		 }
		
	}
	
	

}
