package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.adapter.ExpandableListAdapter;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrderList;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.task.BookingRefreshTask;
import deryl.jibin.clyde.ovenfresh.task.CancelUserTask;
import deryl.jibin.clyde.ovenfresh.task.InitialDataTask;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MYBookingActivity extends ParentActivity {

	    ExpandableListAdapter listAdapter;
	    ExpandableListView expListView;
	    List<String> listDataHeader;
	    HashMap<String, List<BookedOrder>> listDataChild;
	    List<Order> listOrderNumber;
	    boolean isFromButtonClick = false;
	    String ActivityName = "abc";
	    TextView mTextView;
	    String orderId;
	    
	    public  void createList()
	    {
	    	
	    	Log.d("DERYL", "CREATE A LIST ");
	    	 // preparing list data
	        listDataHeader = new ArrayList<String>();
	        listDataChild = new HashMap<String, List<BookedOrder>>();
	        listOrderNumber = new ArrayList<Order>();
	        
	        // Adding child data
	        listDataHeader.add("Pending Order");
	        listDataHeader.add("Ready Order");
	        listDataHeader.add("Past Order");
	        listDataHeader.add("Cancel Order");
	        if(Utility.sharedPrefence == null)
			{
				 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
			}
		    
	        String mobileNo = Utility.sharedPrefence.getString(Constants.MobileNumberTag, "");
	    	if(!mobileNo.trim().equals("") && mobileNo.length() == 10)
			{
	    		if(BookedOrderList.getInstance()!= null && BookedOrderList.getInstance().getBookedOrderList() != null && BookedOrderList.getInstance().getReadyOrderList() != null && BookedOrderList.getInstance().getCompleteOrderList() != null &&  BookedOrderList.getInstance().getCancelOrderList() != null)
	    		{
	    			listDataChild.put(listDataHeader.get(0), BookedOrderList.getInstance().getBookedOrderList()); 
	    			listDataChild.put(listDataHeader.get(1), BookedOrderList.getInstance().getReadyOrderList());
	    			listDataChild.put(listDataHeader.get(2), BookedOrderList.getInstance().getCompleteOrderList());
	    			listDataChild.put(listDataHeader.get(3), BookedOrderList.getInstance().getCancelOrderList());
	    		}
	    		else
	    		{
	    			Log.d("DERYL", "BOOKED ORDER INSTANCE IS NULL");
	    			expListView.setVisibility(View.GONE);
		    		mTextView.setText(Constants.noBookingMessage);
		    		mTextView.setVisibility(View.VISIBLE);
	    		}
	    		
   	    	   listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
	  	       expListView.setAdapter(listAdapter);
	  	        Log.d("DERYL", "INITIALISE THE VARIABLE");
			}
	    	else
	    	{
	    		expListView.setVisibility(View.GONE);
	    		mTextView.setText(Constants.noBookingMessage);
	    		mTextView.setVisibility(View.VISIBLE);
	    	}
	      
	    	
	    }
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    	 setContentView(R.layout.mybooking_layout);
			super.onCreate(savedInstanceState);
	       
	 
			ActivityName = getIntent().getStringExtra(Constants.activity);
	        expListView = (ExpandableListView) findViewById(R.id.lvExp);
	        mTextView = (TextView) findViewById(R.id.msg_text);
	        
	        
	        Log.d("DERYL", "IS FROM NOTIFICATION "+ Constants.isFromNotification);
	        if(!Utility.isOnline(MYBookingActivity.this))
        	{
    			Utility.showWIFIDisabledAlertToUser(MYBookingActivity.this);
        	}	
    	    else if(Constants.isFromNotification)
    	    {
    	    	new BookingRefreshTask(MYBookingActivity.this).execute();
    	    }
	        createList();
	        
	    }
	    
	    
	    @Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			isFromButtonClick = false;
			Utility.isPresentINVIEWORDER = false;
		}
	 
	    @Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			if(!isFromButtonClick)
			{
				Log.d("DERYL", ">>> finsih activity");
				this.finish();
			}
		}

		public void showItem(View v)
	    {
	    	LinearLayout parent = (LinearLayout) v.getParent().getParent();
	    	
	    	TextView orderid = (TextView) ((LinearLayout)parent.getChildAt(0)).getChildAt(1);
	    	TextView price = (TextView) ((LinearLayout)parent.getChildAt(1)).getChildAt(1);
	    	TextView status = (TextView) ((LinearLayout)parent.getChildAt(4)).getChildAt(1);
	    	
	    	Bundle bundle = new Bundle();
	    	bundle.putString(Constants.orderid, orderid.getText().toString());
	    	bundle.putString(Constants.totalsum, price.getText().toString());
	    	bundle.putString(Constants.status, status.getText().toString());
	    	bundle.putString(Constants.menuactivity,Constants.mybooking);
	    	isFromButtonClick = true;
	    	Log.d("DERYL", ">>> insinde the click");
	    	Intent intent = new Intent(MYBookingActivity.this,ViewOrderActivity.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	    	intent.putExtras(bundle);
	    	startActivityForResult(intent, 11);
	    	
	    }
		
		public void refreshBooking(View v)
		{
			new BookingRefreshTask(MYBookingActivity.this).execute();
			
		}
		
		
		 @Override
			protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				// TODO Auto-generated method stub
				super.onActivityResult(requestCode, resultCode, data);
				finish();
				
			}
		
		
		@Override
		public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish(); 
		if( Constants.isFromNotification || (ActivityName!= null && !ActivityName.equals("") && ActivityName.equals(Constants.reportactivity)))
		{
			Constants.isFromNotification = false;
			Intent intent = new Intent(MYBookingActivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		
		}
		
		 public void cancelOrder(View v)
		 {
			 LinearLayout parent = (LinearLayout) v.getParent().getParent();
			 TextView orderid = (TextView) ((LinearLayout)parent.getChildAt(0)).getChildAt(1);
			 orderId = orderid.getText().toString();
			 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						MYBookingActivity.this);
					// set title
					alertDialogBuilder.setTitle(R.string.cancelorder);
					// set dialog message
					alertDialogBuilder
						.setMessage(Constants.alertCancelOrderMessage)
						.setCancelable(false)
						.setPositiveButton(Constants.alertMessageYes,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								
								new CancelUserTask(MYBookingActivity.this, dialog,orderId, listDataHeader,  expListView).execute();
							}
						  })
						.setNegativeButton(Constants.alertMessageNo,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
							}
						});
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
						// show it
						alertDialog.show();
		 }

		
}
