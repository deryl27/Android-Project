package deryl.jibin.clyde.ovenfresh.adapter;

import java.util.ArrayList;
import java.util.List;

import deryl.jibin.clyde.ovenfresh.R;

import deryl.jibin.clyde.ovenfresh.entity.Category;
import deryl.jibin.clyde.ovenfresh.utils.Constants;

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

public class CategoryAdapter extends BaseAdapter 
{
	private Activity mActivity;
	private List<Category> mCategory;
	private LayoutInflater mLayoutInflater;
	
	
	 public CategoryAdapter(Activity mActivity, List<Category> mCategory) {
	        this.mActivity = mActivity;
	        this.mCategory = mCategory;		
	        mLayoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCategory.size();
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
		
		View vi=convertView;
        if(convertView==null)
            vi = mLayoutInflater.inflate(R.layout.list_category_product_item, null);
 
        ImageView productimage = (ImageView)vi.findViewById(R.id.product_image);
        Button productbutton = (Button)vi.findViewById(R.id.product_button);
        TextView text = (TextView) vi.findViewById(R.id.categoryname);
        text.setText(mCategory.get(position).getCategoryName().toString());
        String name = mCategory.get(position).getCategoryName().toString();
        Log.d("DERYL", "NAME IN CATEGORY>>>>>." + name);
//        if(name.equals("Cakes"))
//        {
//        	productimage.setBackgroundResource(R.drawable.shopcake);
//        	productbutton.setText(Constants.shopCakes);
//        }
//        else
//        {
//        	productimage.setBackgroundResource(R.drawable.shopsweets);
//        	productbutton.setText(Constants.shopSweet);
//        }
        
        return vi;
	}
	

}
