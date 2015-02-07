package com.example.weatehrapi;

import Utils.Utility;
import adapter.LazyAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class LocationActivity extends Activity {

	ListView cityListView; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.location);
	    
	    Utility.setLocationActionBar(this);
	    
	    cityListView = (ListView) findViewById(R.id.city);
	    
	    LazyAdapter  adapter = new LazyAdapter(this, Utility.getListOfCity(getApplicationContext()));
	    cityListView.setAdapter(adapter);
	    
	    
	}

	
	
	
}
