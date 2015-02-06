package deryl.jibin.clyde.ovenfresh.adapter;

import java.util.ArrayList;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.R;

import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter 
{

	private Activity mActivity;
	private List<OrderProduct> mOrderProducts;
	private LayoutInflater mLayoutInflater;
	private boolean isEditButtonVisible;
	
	 public OrderAdapter(Activity mActivity, List<OrderProduct> mOrderProduct, boolean isVisible) 
	 {
	        this.mActivity = mActivity;
	        this.mOrderProducts = mOrderProduct;
	        this.isEditButtonVisible = isVisible;
	        mLayoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mOrderProducts.size();
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
		Log.d("DERYL", "INSIDE VIEW >>>>>>>>");
	 	View vi=convertView;
        if(convertView==null)
            vi = mLayoutInflater.inflate(R.layout.order_item_layout, null);
        
        TextView orderName = (TextView) vi.findViewById(R.id.order_name);
        TextView orderPrice = (TextView) vi.findViewById(R.id.order_price);
        TextView orderItem = (TextView) vi.findViewById(R.id.order_item);
        TextView orderQuantity = (TextView) vi.findViewById(R.id.order_quantity);
        TextView totalsumtext = (TextView) vi.findViewById(R.id.total_sum);
        TextView productPostion = (TextView) vi.findViewById(R.id.product_position);
        ImageView editButton = (ImageView) vi.findViewById(R.id.editProductButton);
        ImageView deleteButton = (ImageView) vi.findViewById(R.id.deleteProductButton);
        if(isEditButtonVisible)
        {
        	editButton.setVisibility(View.VISIBLE);
        	deleteButton.setVisibility(View.VISIBLE);
        }
        else
        {
        	editButton.setVisibility(View.GONE);
        	deleteButton.setVisibility(View.GONE);
        }
        orderName.setText(mOrderProducts.get(position).getProductName());
        orderPrice.setText(mOrderProducts.get(position).getProductActualPrice());
        orderQuantity.setText(mOrderProducts.get(position).getProductQuantity());
        orderItem.setText(mOrderProducts.get(position).getProductItemCount());
        productPostion.setText(position+"");
        
        return vi;
	}
	

}
