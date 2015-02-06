package deryl.jibin.clyde.ovenfresh.task;

import java.util.HashMap;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.ErrorActivity;
import deryl.jibin.clyde.ovenfresh.MYBookingActivity;
import deryl.jibin.clyde.ovenfresh.ProfileActivity;
import deryl.jibin.clyde.ovenfresh.adapter.ExpandableListAdapter;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrderList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;

import Utils.JsonHelper;
import Utils.ServerInteraction;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

public class UpdateProfileTask extends AsyncTask<Void, Integer, Void>
{
	private Activity mContext;
	ProgressDialog pd;
	boolean isTaskSuccessfull = true;
    String mobileNo ; 
    String newNo ;
    String firstName ;
    String lastName ;
    String email;
    int userStatus;
    
    public UpdateProfileTask(Activity mContext, String mobileNo ,String newNo, String firstName ,
    		String lastName, String email)
    {
    	this.mContext = mContext;
    	this.mobileNo = mobileNo;
    	this.newNo = newNo;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.email = email;
    	
    }
    
    
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		pd = new ProgressDialog(mContext);
		pd.setTitle(Constants.updateMessageTitle);
		pd.setMessage(Constants.updateMessage);
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}
	

	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		
		try
		{
			String data = ServerInteraction.getJson(Constants.updateProfie+newNo+"&mobileNo="+mobileNo+"&firstname="+firstName+"&lastname="+lastName+"&email="+email);
			Log.d("DERYL","data"+data);
			if(data != null)
			{
				userStatus = JsonHelper.getUpdateProfile(data);
			}
			else
			{
				Log.d("DERYL", ">>> NO CUSTOMER UPDATE FOUND >>>>>>>>");
				isTaskSuccessfull = false;
			}
			return null;
		}
		catch (Exception e) {
			// TODO: handle exception
			Log.d("DERYL","data>>>>>>>>>>");
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
		 if(isTaskSuccessfull)
		 {
				 Log.d("DERYL", "USERSTATUS "+ userStatus);
			    if(userStatus == 1)
			    {
			    	((ProfileActivity) mContext).updatePreferences();
			    }
			    else
			    {
			    	Toast.makeText(mContext, Constants.latermessage, Toast.LENGTH_SHORT).show();
			    }
		 }
		 else
		 {
			 Intent intent = new Intent(mContext, ErrorActivity.class);
			 mContext.startActivity(intent);
		 }
		
		
	}

}
