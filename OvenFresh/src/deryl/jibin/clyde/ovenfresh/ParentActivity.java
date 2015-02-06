package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class ParentActivity extends Activity {

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
// 	    if(Constants.isRegActivity)
//	    {
//	    	Log.d("DERYL", "REG LAYOUT");
//	    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.reg_title);
//	    	Constants.isRegActivity = false;
//	    }
//	    else
	    //{
	    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_menu);
		 	
	    //}
	}
	
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		setCartCount();
	}




	public void setCartCount()
	{
		int count;
		if( OrderProductList.getInstance().getOrderList() == null )
        {
			count = 0;
        }
        else
        {
        	count = OrderProductList.getInstance().getOrderList().size();
        }
		
		final TextView addtoCartTextView = (TextView) this.findViewById(R.id.cart_number);
		addtoCartTextView.setText(count+"");
	}
	
	
	
	public void displayOrderedItem(View v)
	{
		Log.d("DERYL", ">>>>>> INSIDE THE CART FUNCTIONALITY");
		
		if(OrderProductList.getInstance() != null && OrderProductList.getInstance().getOrderList() != null &&OrderProductList.getInstance().getOrderList().size() > 0)
		{	
			Intent intent =  new Intent(ParentActivity.this, OrderActivity.class);
			startActivity(intent);
		}
		else
		{
			Toast.makeText(ParentActivity.this,Constants.cart_error , Toast.LENGTH_SHORT).show();
		}
	}
	
	public void displaymenu(View v)
	{
		
		ImageView image = (ImageView) findViewById(R.id.menu);
		ListView mListView = new ListView(ParentActivity.this);
		List<String> popup = new ArrayList<String>();
		popup.add("My Booking");
		popup.add("Cancel Order");
		popup.add("Profile");
		popup.add("Contact US");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, popup);
		mListView.setAdapter(adapter);
	    
		PopupMenu popupMenu = new PopupMenu(ParentActivity.this, image);
		
		popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
		
		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Log.d("DERYL", "menu get groupid "+ item.getTitle());
				if(item.getTitle().equals(Constants.mybooking))
				{
					Intent intent = new Intent(ParentActivity.this, MYBookingActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(intent);
				}
				else if(item.getTitle().equals(Constants.contactus))
				{
					Intent intent = new Intent(ParentActivity.this, ContactUsActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(intent);
				}
				else if(item.getTitle().equals(Constants.profile))
				{
					Intent intent = new Intent(ParentActivity.this, ProfileActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(intent);
				}
				return true;
			}
		});
		popupMenu.show();
		
	}
	
	public void gobackToHome(View v)
	{
		Intent intent = new Intent(ParentActivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
