package deryl.jibin.clyde.ovenfresh;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class PopUpActivity extends Activity{
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.customize_title_menu);
	}
	
	
	public void gobackToHome(View v)
	{
		Intent intent = new Intent(PopUpActivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
