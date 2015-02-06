package deryl.jibin.clyde.ovenfresh.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import deryl.jibin.clyde.ovenfresh.R;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.utils.Constants;

public class CancelOrderAdapter extends BaseAdapter 
{
	private Activity mActivity;
	private List<BookedOrder> mOrderList;
	private LayoutInflater mLayoutInflater;
	
	
	 public CancelOrderAdapter(Activity mActivity, List<BookedOrder> mOrderList) {
	        this.mActivity = mActivity;
	        this.mOrderList = mOrderList;		
	        mLayoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mOrderList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		 if (convertView == null) {
             LayoutInflater infalInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             
             convertView = infalInflater.inflate(R.layout.list_data, null);
         }
  
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView orderid = (TextView) convertView.findViewById(R.id.orderid);
        TextView deliverydate = (TextView) convertView.findViewById(R.id.deliverydate);
        TextView deliverytime = (TextView) convertView.findViewById(R.id.deliverytime);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        Button cancelbutton = (Button) convertView.findViewById(R.id.cancel_order_button);
        price.setText(mOrderList.get(position).getOrderDetails().getPrice()+"");
        orderid.setText(mOrderList.get(position).getOrderDetails().getOrderId());
        deliverydate.setText(mOrderList.get(position).getOrderDetails().getOrderDeliveryDate());
        deliverytime.setText(mOrderList.get(position).getOrderDetails().getDeliveryTime());
        status.setText(mOrderList.get(position).getOrderDetails().getStatus());
       
        String orderDate = mOrderList.get(position).getOrderDetails().getOrderGeneratedTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try
		{
			  date = formatter.parse(orderDate);
		}
		catch (Exception e) {
		}
		 Calendar cal = Calendar.getInstance(); // creates calendar
		 cal.setTime(date);
		 cal.add(Calendar.HOUR_OF_DAY, 6);
		 Calendar currentTime = Calendar.getInstance();
		 if(cal.getTimeInMillis() > currentTime.getTimeInMillis())
		 {
			 cancelbutton.setVisibility(View.VISIBLE); // Show Cancel Button 
		 }
		 else
		 {
			 cancelbutton.setVisibility(View.GONE); // No Cancel Button 
		 }
		 if(mOrderList.get(position).getOrderDetails().getStatus().equals(Constants.cancelStatus))
		 {
			 cancelbutton.setVisibility(View.GONE);
		 }
		 
		 
        	return convertView;
	}
	

}
