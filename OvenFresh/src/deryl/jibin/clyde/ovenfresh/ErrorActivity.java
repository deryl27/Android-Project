package deryl.jibin.clyde.ovenfresh;


import deryl.jibin.clyde.ovenfresh.utils.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ErrorActivity extends Activity {
	
	TextView mTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.error);
	    
	    mTextView = (TextView) findViewById(R.id.error_text);
	    mTextView.setText(Constants.connectivityError);
	    
	}

}
