package deryl.jibin.clyde.ovenfresh;



import deryl.jibin.clyde.ovenfresh.task.InitialDataTask;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends Activity {

	
	// used to know if the back button was pressed in the splash screen activity and avoid opening the next activity
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 500; // 2 seconds
    private static final int SPLASH_DURATION_RESUME = 2000; // 2 seconds
    boolean firstTime = true;
    Context mContext;
    
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.splash_screen);
	    // TODO Auto-generated method stub
	    
	    mContext = SplashActivity.this;
	    
	    Handler handler = new Handler();
	    Log.d("DERYL", "onCREATE");
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {
 
            @Override
            public void run() {
                // make sure we close the splash screen so the user won't come back when it presses back key
               // finish();
//                if (!mIsBackButtonPressed) {
//                    // start the home screen if the back button wasn't pressed already 
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    SplashActivity.this.startActivity(intent);
//               }
            	if(!Utility.isOnline(SplashActivity.this))
            	{
        	    	Utility.showWIFIDisabledAlertToUser(SplashActivity.this);
            	}	
        	    else
        	    {
        	    	Log.i("DERYL", "ONCREATE THE APPLICATION >>>>>>");
        	    	new InitialDataTask(SplashActivity.this).execute();
        	    }
            }
        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
	}
	
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("DERYL", "onResume");
	    if (firstTime){
	    	Log.i("DERYL", "it's the first time");
	    	firstTime = false;
	    }
	    else
	    {
	    	Log.i("DERYL", "it's not the first time");
	        Handler handler = new Handler();
	        handler.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	        	    if(!Utility.isOnline(SplashActivity.this))
	            	{
	        	    	Utility.showWIFIDisabledAlertToUser(SplashActivity.this);
	            	}	
	        	    else
	        	    {
	        	    	Log.i("DERYL", "TowARDS THE APPLICATION >>>>>>");
	        	    	new InitialDataTask(SplashActivity.this).execute();
	        	    }
	            }
	        }, SPLASH_DURATION_RESUME);
	    }
	}
	
	
	
	 
	 

	
	@Override
	public void onBackPressed() 
	{
	        // set the flag to true so the next activity won't start up
	        mIsBackButtonPressed = true;
	        super.onBackPressed();
	 
    }

}
