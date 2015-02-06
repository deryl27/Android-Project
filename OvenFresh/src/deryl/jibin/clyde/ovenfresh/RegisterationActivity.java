package deryl.jibin.clyde.ovenfresh;


import deryl.jibin.clyde.ovenfresh.task.GCMRegistrationTask;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterationActivity extends Activity {

	EditText regNumber; 
	TextView regError;
	boolean isLoadFirstTime = false;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.registeration_activity);
	    regNumber = (EditText) findViewById(R.id.reg_mobile_number);
	    regError = (TextView) findViewById(R.id.reg_message);
	    if(Utility.sharedPrefence == null)
		{
			 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
		}
	    isLoadFirstTime = Utility.sharedPrefence.getBoolean(Constants.isLoad , true);
	    if(isLoadFirstTime)
    	{
    		isLoadFirstTime =false;
    		showCityDialog();
    	}
	    
	}
	
	public void showCityDialog()
	{
		if(Utility.sharedPrefence == null)
		{
			 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
		}
		Editor editor = Utility.sharedPrefence.edit();
 	    editor.putBoolean(Constants.isLoad, isLoadFirstTime);
	    editor.commit(); 
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterationActivity.this);
				// set title
				alertDialogBuilder.setTitle(R.string.note);
				// set dialog message
				alertDialogBuilder
					.setMessage(Constants.mumbaiorders)
					.setCancelable(false)
					.setPositiveButton(Constants.alertMessageOk,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
						   dialog.cancel();	
						}
					  });
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
					// show it
					alertDialog.show();
	}
	
	public void Registeration(View v)
	{
		Log.d("DERYL","ON CLICK");
		
		if(regNumber.getText().toString() != null && regNumber.getText().toString().trim().length() == 10)
		{
			regError.setVisibility(View.GONE);
			new GCMRegistrationTask(RegisterationActivity.this, regNumber.getText().toString()).execute();
		}
		else
		{
			Log.d("DERYL","ON ELSE  CLICK");
			regError.setVisibility(View.VISIBLE);
			regError.setText(Constants.ERORR);
		}
		
	}

}
