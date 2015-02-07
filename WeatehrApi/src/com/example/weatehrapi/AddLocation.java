package com.example.weatehrapi;

import java.util.Iterator;
import java.util.Set;

import Utils.Constants;
import Utils.Utility;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class AddLocation extends Activity {

	Spinner citySpinner;
	String currentCity = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

		setContentView(R.layout.add_city_layout);

		citySpinner = (Spinner) findViewById(R.id.location_array);
		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				currentCity = parent.getItemAtPosition(pos).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// If we've received a touch notification that the user has touched
		// outside the app, finish the activity.
		if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
			return true;
		}

		// Delegate everything else to Activity.
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void submitLocation(View v) {
		if (currentCity != null || currentCity.trim().length() >= 0) {
			Set<String> location = Utility
					.loadSavedPreferences(getApplicationContext());
			// CHECK THE SIZE
            
			Iterator<String> iterator =  location.iterator();
			location.add(currentCity);
			Utility.savePreferences(getApplicationContext(), location);
			Constants.isFromLocation = true;
			finish();
		} else {
			Toast.makeText(AddLocation.this, Constants.errorLocations,
					Toast.LENGTH_SHORT);
		}

	}

	public void cancel(View v) {
		finish();
	}

}
