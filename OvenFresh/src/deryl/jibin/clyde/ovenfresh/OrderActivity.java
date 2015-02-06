package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.adapter.CancelOrderAdapter;
import deryl.jibin.clyde.ovenfresh.adapter.OrderAdapter;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends ParentActivity {
	ListView mListView;
	TextView totalSumTextView;
	OrderAdapter mOrderAdapter;
	TextView addtoCartTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.order_layout);
	    super.onCreate(savedInstanceState);
	
	    addtoCartTextView = (TextView) this.findViewById(R.id.cart_number);
	    mListView = (ListView) findViewById(R.id.list_order);
	    totalSumTextView = (TextView) findViewById(R.id.total_sum);
	    
	    Log.d("DERYL",">>>>>>"+OrderProductList.getInstance().getOrderList().size());
	    
	    List<OrderProduct> mOrderProducts = OrderProductList.getInstance().getOrderList();
	    double totalSum = 0;
	    for (OrderProduct orderProduct : mOrderProducts) {
			totalSum+=Double.parseDouble(orderProduct.getProductActualPrice());
		}
	    
	    mOrderAdapter = new OrderAdapter(this, OrderProductList.getInstance().getOrderList(), true);
	    mListView.setAdapter(mOrderAdapter);
	    totalSumTextView.setText(totalSum+"");
	    
	    
	}
	
	public void proceedToCheckOut(View v)
	{
		Intent intent = new Intent(OrderActivity.this, CustomerActivity.class);
		startActivity(intent);
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
	
	
	private void goBackToHomePage()
	{
		Intent intent = new Intent(OrderActivity.this, MainActivity.class); 
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
		
		// NEED TO HANDLE EMPTY OF ORDER LIST AND CART CONDITION 
	}
	
	public void editOrderList(View v)
	{
		Intent intent  = new Intent(OrderActivity.this, QuantityActivity.class);
		LinearLayout parent = (LinearLayout)v.getParent();
		int position = Integer.parseInt((((TextView)parent.getChildAt(0)).getText().toString()));
		intent.putExtra(Constants.editPostion,position);
		intent.putExtra(Constants.activity,"OrderActivity");
		startActivity(intent);
		
	}
	
	public void removeOrderList(final View v)
	{
		
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					OrderActivity.this);
				// set title
				alertDialogBuilder.setTitle(R.string.removeItem);
				// set dialog message
				alertDialogBuilder
					.setMessage(Constants.alertRemoveOrderMessage)
					.setCancelable(false)
					.setPositiveButton(Constants.alertMessageYes,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
						    if(OrderProductList.getInstance().getOrderList() != null)
						    {
						    	LinearLayout parent = (LinearLayout)v.getParent();
								int position = Integer.parseInt((((TextView)parent.getChildAt(0)).getText().toString()));
						    	OrderProductList.getInstance().getOrderList().remove(position);
						    	  List<OrderProduct> mOrderProducts = OrderProductList.getInstance().getOrderList();
						  	    double totalSum = 0;
						  	    for (OrderProduct orderProduct : mOrderProducts) {
						  			totalSum+=Double.parseDouble(orderProduct.getProductActualPrice());
						  		}
						  	    
						  	    mOrderAdapter = new OrderAdapter(OrderActivity.this, OrderProductList.getInstance().getOrderList(), true);
						  	    mListView.setAdapter(mOrderAdapter);
						  	    totalSumTextView.setText(totalSum+"");
						  	    
						  	    int count = OrderProductList.getInstance().getOrderList().size();
								addtoCartTextView.setText(count+"");
								
								if(count == 0)
								{
									Intent intent = new Intent(OrderActivity.this, MainActivity.class);
									intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(intent);
								}
						    }
							dialog.cancel();
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
