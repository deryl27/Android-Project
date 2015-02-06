package deryl.jibin.clyde.ovenfresh;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TermsConditionsActivity extends PopUpActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.terms);
	    super.onCreate(savedInstanceState);
	
	}

}
