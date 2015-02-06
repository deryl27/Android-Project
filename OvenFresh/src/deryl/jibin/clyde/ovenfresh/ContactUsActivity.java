package deryl.jibin.clyde.ovenfresh;


import deryl.jibin.clyde.ovenfresh.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ContactUsActivity extends PopUpActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.contactus);
	    super.onCreate(savedInstanceState);
	    
	    
	}

	public void openEmail(View v)
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {Constants.emailAddres});
		intent.putExtra(Intent.EXTRA_SUBJECT, "");
		intent.putExtra(Intent.EXTRA_TEXT, "");
		startActivity(Intent.createChooser(intent, ""));
	}
	
}
