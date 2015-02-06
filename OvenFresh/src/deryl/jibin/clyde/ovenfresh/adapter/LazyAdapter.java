package deryl.jibin.clyde.ovenfresh.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import deryl.jibin.clyde.ovenfresh.R;

import deryl.jibin.clyde.ovenfresh.entity.Cakes;
import deryl.jibin.clyde.ovenfresh.entity.Product;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
	
	private Activity mActivity;
	private List<Product> mProduct;
	private LayoutInflater mLayoutInflater;
	
	
	 public LazyAdapter(Activity mActivity, List<Product> mProduct) {
	        this.mActivity = mActivity;
	        this.mProduct = mProduct;		
	        mLayoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mProduct.size();
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
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		 	View vi=convertView;
	        if(convertView==null)
	            vi = mLayoutInflater.inflate(R.layout.list_row_product, null);
	 
	        TextView productName = (TextView)vi.findViewById(R.id.product_name); 
	        TextView productPrice = (TextView)vi.findViewById(R.id.product_price); 
	        TextView productId = (TextView) vi.findViewById(R.id.product_id);
	        TextView productCategory = (TextView) vi.findViewById(R.id.product_category);
	        productId.setText(mProduct.get(position).getProductId());
	        productName.setText(mProduct.get(position).getProductName());
	        productPrice.setText("Rs "+mProduct.get(position).getProductPrice()+"/kg");
	        productCategory.setText(mProduct.get(position).getProductCategory());
	    	return vi;
	}
	
	

}
