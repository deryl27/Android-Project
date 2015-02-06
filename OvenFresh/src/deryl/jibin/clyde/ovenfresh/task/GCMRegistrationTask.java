package deryl.jibin.clyde.ovenfresh.task;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import deryl.jibin.clyde.ovenfresh.ErrorActivity;
import deryl.jibin.clyde.ovenfresh.MainActivity;
import deryl.jibin.clyde.ovenfresh.RegisterationActivity;
import deryl.jibin.clyde.ovenfresh.SplashActivity;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import Utils.ServerInteraction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class GCMRegistrationTask extends AsyncTask<Void, Integer, Void>
{
	  private Context mContext;
	  GoogleCloudMessaging gcm;
	  boolean isTaskSuccessfull = true;
	  String mobileNo;
	  ProgressDialog pd;
	  
	  public GCMRegistrationTask(Context mContext, String mobileNo)
	  {
		  this.mContext = mContext;
		  this.mobileNo = mobileNo ;
	  }
	  
	  @Override
		protected void onPreExecute() 
		{
		   // update the UI immediately after the task is executed
		   super.onPreExecute();
		   pd = new ProgressDialog(mContext);
	       pd.setTitle(Constants.LoadingContentTitle);
	       pd.setMessage(Constants.LoadingContentMessage);
	       pd.setCancelable(false);
	       pd.setIndeterminate(true);
	       pd.show();
		  
	    }
	
	   @Override
	   protected Void doInBackground(Void... params) 
	   {
         try 
         {
            if (gcm == null) 
            {
                 gcm = GoogleCloudMessaging.getInstance(mContext);
            }
            Constants.REGID = gcm.register(Constants.projectNumber);
        	if(Utility.sharedPrefence == null)
			{
				 Utility.sharedPrefence = mContext.getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
			}
			Editor editor = Utility.sharedPrefence.edit();
     	    editor.putString(Constants.REGIDConstant, Constants.REGID);
     	    editor.putString(Constants.MobileNumberTag, mobileNo);
		    editor.commit(); 
		    // save the regid in the server 
		    ServerInteraction.getJson(Constants.registeration+mobileNo+"&regid="+Constants.REGID);
		    Log.d("DERYL",  Constants.REGID );

          } 
          catch (IOException ex) 
          {
        	  Log.d("DERYL", "Error :" + ex.getMessage());
        	  isTaskSuccessfull = false;	
          }
         
         return null;
       }
	   
	   @Override
        protected void onPostExecute(Void result) 
	   {
		   if (pd!=null) {
				pd.dismiss();
			}
		   ((RegisterationActivity)mContext).finish();
		   // Close the login Activity
		   if(isTaskSuccessfull)
			{
				Intent intent = new Intent(mContext, MainActivity.class);
				mContext.startActivity(intent);
			}
			else
			{
				Intent intent = new Intent(mContext, ErrorActivity.class);
				mContext.startActivity(intent);
			}
		   
       }
 }

