package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.adapter.CancelOrderAdapter;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrderList;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.task.CancelUserTask;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CancelOrderActivity extends ParentActivity {
	
	ListView mCancelOrderListView;
	List<BookedOrder> orderList = new ArrayList<BookedOrder>();
	String orderId;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.cancel_order);
		super.onCreate(savedInstanceState);
		
		mCancelOrderListView = (ListView) findViewById(R.id.cancelorderlist);
		
		if(BookedOrderList.getInstance() !=null && BookedOrderList.getInstance().getBookedOrderList() != null && BookedOrderList.getInstance().getBookedOrderList().size() > 0)
		{
			orderList = BookedOrderList.getInstance().getBookedOrderList();
			CancelOrderAdapter adapter = new CancelOrderAdapter(CancelOrderActivity.this, orderList);
			mCancelOrderListView.setAdapter(adapter);
			
		}
		
		
	}
	
	 @Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			//isFromButtonClick = false;
			Utility.isPresentINVIEWORDER = false;
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
	    	bundle.putString(Constants.menuactivity,Constants.cancelorder);
	    	Intent intent = new Intent(CancelOrderActivity.this,ViewOrderActivity.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	    	intent.putExtras(bundle);
	    	startActivityForResult(intent, 10);
	    	
	    }
	 
	 public void cancelOrder(View v)
	 {
		 LinearLayout parent = (LinearLayout) v.getParent().getParent();
		 TextView orderid = (TextView) ((LinearLayout)parent.getChildAt(0)).getChildAt(1);
		 orderId = orderid.getText().toString();
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					CancelOrderActivity.this);
				// set title
				alertDialogBuilder.setTitle(R.string.cancelorder);
				// set dialog message
				alertDialogBuilder
					.setMessage(Constants.alertCancelOrderMessage)
					.setCancelable(false)
					.setPositiveButton(Constants.alertMessageYes,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							//new CancelUserTask(CancelOrderActivity.this, dialog,orderId, mCancelOrderListView).execute();
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
		
		}
		

}
