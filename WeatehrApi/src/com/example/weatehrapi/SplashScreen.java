package com.example.weatehrapi;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;

public class SplashScreen extends Activity {

	 private static final int SPLASH_DURATION = 1000;
	    boolean isBackPressed = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.splashscreen);
	    // TODO Auto-generated method stub
	    Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run()
            {
                if (!isBackPressed) {
                    Intent intentMain = new Intent(SplashScreen.this, MainActivity.class);

                    startActivity(intentMain);
                    finish();
                }
                else
                {
                    isBackPressed = false;
                }
            }
        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called

    }





    @Override
    public void onBackPressed() {
        isBackPressed = true;
        super.onBackPressed();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isBackPressed = true;
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
