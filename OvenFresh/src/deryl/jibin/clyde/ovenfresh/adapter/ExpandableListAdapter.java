package deryl.jibin.clyde.ovenfresh.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import deryl.jibin.clyde.ovenfresh.R;

import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
 

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<BookedOrder>> _listDataChild;
 
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<BookedOrder>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return childPosititon;
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
    	 if (convertView == null) {
             LayoutInflater infalInflater = (LayoutInflater) this._context
                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             
             convertView = infalInflater.inflate(R.layout.list_data, null);
         }
  
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView orderid = (TextView) convertView.findViewById(R.id.orderid);
        TextView deliverydate = (TextView) convertView.findViewById(R.id.deliverydate);
        TextView deliverytime = (TextView) convertView.findViewById(R.id.deliverytime);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        Button cancelbutton = (Button) convertView.findViewById(R.id.cancel_order_button);
        
        price.setText(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getPrice()+"");
        orderid.setText(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getOrderId());
        deliverydate.setText(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getOrderDeliveryDate());
        deliverytime.setText(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getDeliveryTime());
        status.setText(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getStatus());
        
        Log.d("DERYL", " orderID "+ _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getOrderId());
        String orderDate = _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getOrderGeneratedTime();
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
		 if(status.getText().equals(Constants.pendingStatus) && cal.getTimeInMillis() > currentTime.getTimeInMillis())
		 {
			 cancelbutton.setVisibility(View.VISIBLE); // Show Cancel Button 
		 }
		 else
		 {
			 cancelbutton.setVisibility(View.GONE); // No Cancel Button 
		 }
		 if(_listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getOrderDetails().getStatus().equals(Constants.cancelStatus))
		 {
			 cancelbutton.setVisibility(View.GONE);
		 }
        	return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

