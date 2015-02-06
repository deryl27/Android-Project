package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.adapter.OrderAdapter;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrderList;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class ViewOrderActivity extends ParentActivity {
	
	ListView mListView;
	TextView totalSumTextView,orderid;
	OrderAdapter mOrderAdapter;
	List<OrderProduct> top250 = new ArrayList<OrderProduct>();
	String backActivityText;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.view_menu_order);
		super.onCreate(savedInstanceState);
		
		  mListView = (ListView) findViewById(R.id.view_list);
		  orderid = (TextView) findViewById(R.id.view_orderid);
		  totalSumTextView = (TextView) findViewById(R.id.view_totalsum);
		  
		
		  totalSumTextView.setText(getIntent().getExtras().getString(Constants.totalsum));
		  orderid.setText(getIntent().getExtras().getString(Constants.orderid));
		  String orderId = getIntent().getExtras().getString(Constants.orderid);
		  String status = getIntent().getExtras().getString(Constants.status);
		  List<BookedOrder> mBookedOrderLists = null;
		  if(status.equals(Constants.pendingStatus))
		  {
			  Log.d("DERYL", "PENDING STATUS");
			mBookedOrderLists =  BookedOrderList.getInstance().getBookedOrderList();
		  }
		  else if(status.equals(Constants.readyStatus))
		  {
			  Log.d("DERYL", "READY STATUS");
			  mBookedOrderLists =  BookedOrderList.getInstance().getReadyOrderList();  
		  }
		  else if(status.equals(Constants.completeStatus))
		  {
			  Log.d("DERYL", "COMPLETE STATUS");
			  mBookedOrderLists = BookedOrderList.getInstance().getCompleteOrderList();
		  }
		  else if(status.equals(Constants.cancelStatus))
		  {
			  Log.d("DERYL", "CANCEL STATUS");
			  mBookedOrderLists = BookedOrderList.getInstance().getCancelOrderList();
		  }
		  
		  Log.d("DERYL", "LENGTH OF mBookedOrderLists"+ mBookedOrderLists.size());
		  
		  Iterator<BookedOrder> iterate = mBookedOrderLists.iterator();
		  List<OrderProduct> mOrderProducts = null;
		  while(iterate.hasNext())
		  {
			  BookedOrder temp = iterate.next();
			  if(temp.getOrderDetails().getOrderId().equals(orderId))
			  {
				  mOrderProducts = temp.getOrderItem();
				  break;
			  }
			  
		  }
		  
		  mOrderAdapter = new OrderAdapter(this,mOrderProducts, false);
		  mListView.setAdapter(mOrderAdapter);
		  backActivityText = getIntent().getExtras().getString(Constants.menuactivity);
		  Utility.isPresentINVIEWORDER = true;
		
	}
	
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	if(backActivityText.equals(Constants.mybooking))
    	{
    		Intent intent = new Intent(ViewOrderActivity.this, MYBookingActivity.class);
    		startActivity(intent);
    	}
    	else if(backActivityText.equals(Constants.cancelorder))
    	{
    		Intent intent = new Intent(ViewOrderActivity.this, CancelOrderActivity.class);
    		startActivity(intent);
    	}
    }

}
