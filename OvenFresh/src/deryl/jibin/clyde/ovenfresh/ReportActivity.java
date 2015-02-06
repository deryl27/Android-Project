package deryl.jibin.clyde.ovenfresh;

import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ReportActivity extends ParentActivity {
	TextView report;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.order_status);
	    super.onCreate(savedInstanceState);
		
	    report = (TextView) findViewById(R.id.order_report);
	    report.setText("Your Order has been Submitted \nThe Order id is "+Constants.orderID+". \nTo check the status of your booking click on MY BOOKING  ");
	    
	    OrderProductList.setInstance();
        final TextView addtoCartTextView = (TextView) this.findViewById(R.id.cart_number);
		addtoCartTextView.setText("0");
	    
	}

	
	public void proceedToCategory(View v)
	{
		goBackToHomePage();
	}
	
	public void proceedToMyBooking(View v)
	{
		goBackMyBooking();
	}
	
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
        if ((keyCode == KeyEvent.KEYCODE_BACK)) 
        {
        	Log.d("DERYL", "KEY BACK PRESSED");
        	goBackToHomePage();
            return true; 
        }
        return super.onKeyDown(keyCode, event);
	}
	
	
	private void goBackMyBooking()
	{
		Intent intent = new Intent(ReportActivity.this, MYBookingActivity.class); 
		intent.putExtra(Constants.activity, Constants.reportactivity);
		startActivity(intent);
	}
	
	
	private void goBackToHomePage()
	{
		Intent intent = new Intent(ReportActivity.this, MainActivity.class); 
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
		
		// NEED TO HANDLE EMPTY OF ORDER LIST AND CART CONDITION 
	}
	
	
}
