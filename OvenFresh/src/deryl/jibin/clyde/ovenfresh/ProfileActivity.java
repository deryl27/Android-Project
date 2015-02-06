package deryl.jibin.clyde.ovenfresh;

import java.util.List;


import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.task.SubmitOrderTask;
import deryl.jibin.clyde.ovenfresh.task.UpdateProfileTask;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends PopUpActivity {
	
	EditText mobile, first , last, email, new_mobile;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.profile);
	    super.onCreate(savedInstanceState);
	   
	    
	    mobile = (EditText) findViewById(R.id.profile_mobile);
	    first = (EditText) findViewById(R.id.profile_first_name);
	    last = (EditText) findViewById(R.id.profile_last_name);
	    email = (EditText) findViewById(R.id.profile_email);
	    new_mobile = (EditText) findViewById(R.id.new_mobile);
	    
		if(Utility.sharedPrefence == null)
		{
			 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
		}
    	if (Utility.sharedPrefence.contains(Constants.MobileNumberTag) && Utility.sharedPrefence.contains(Constants.TagLastName) && Utility.sharedPrefence.contains(Constants.TagFirstName)&& Utility.sharedPrefence.contains(Constants.TagEmail))
        {
           mobile.setText(Utility.sharedPrefence.getString(Constants.MobileNumberTag, ""));
           first.setText(Utility.sharedPrefence.getString(Constants.TagFirstName, ""));
           last.setText(Utility.sharedPrefence.getString(Constants.TagLastName, ""));
           email.setText(Utility.sharedPrefence.getString(Constants.TagEmail, ""));
           
        }
    	else
    	{
    		mobile.setText(Utility.sharedPrefence.getString(Constants.MobileNumberTag, ""));
    	}
	}
	
	public void updatePreferences()
	{
		if(Utility.sharedPrefence == null)
		{
			 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
		}
		Editor editor = Utility.sharedPrefence.edit();
 	    editor.putString(Constants.MobileNumberTag, new_mobile.getText().toString());
 	    editor.putString(Constants.TagFirstName, first.getText().toString());
 	    editor.putString(Constants.TagLastName, last.getText().toString());
 	    editor.putString(Constants.TagEmail, email.getText().toString());
 	    editor.commit(); 
 	    
 	    Toast.makeText(ProfileActivity.this, Constants.updateProfile, Toast.LENGTH_SHORT).show();
	}
	
	public void submitProfileOrder(View v)
	{
		if(first.getText().toString().trim().equals("") || last.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") || mobile.getText().toString().trim().equals(""))
		{
		  Toast.makeText(ProfileActivity.this, Constants.customerErrorMessage, Toast.LENGTH_SHORT).show();	
		}
		else
		{
			if(!email.getText().toString().matches(Constants.emailPattern))
			{
				Toast.makeText(ProfileActivity.this, Constants.customerEmailErrorMessage, Toast.LENGTH_SHORT).show();
			}
			else
			{
				if(new_mobile.getText().toString().equals("") && new_mobile.getText().toString().trim().length() < 10)
				{
					Toast.makeText(ProfileActivity.this, Constants.ERORR, Toast.LENGTH_SHORT).show();
				}
				else
				{
					Log.d("DERYL", "Customer DATA ");
					new UpdateProfileTask(this, mobile.getText().toString(), new_mobile.getText().toString(), first.getText().toString(), last.getText().toString(),email.getText().toString()).execute();
				}

			}
		}
	
		
		
	}
}
