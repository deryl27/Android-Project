package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.adapter.CategoryAdapter;
import deryl.jibin.clyde.ovenfresh.entity.Category;
import deryl.jibin.clyde.ovenfresh.entity.CategoryList;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ParentActivity {
	
	ListView mListView;
	List<Category> mCategoryList;
	CategoryAdapter mCategoryAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.list_category_layout);
		super.onCreate(savedInstanceState);

		if(CategoryList.getInstance() != null && CategoryList.getInstance().getCategoryList().size() > 0)
		{
			Log.d("DERYL", "length" + CategoryList.getInstance().getCategoryList().size());
			mCategoryList = CategoryList.getInstance().getCategoryList();
			
		//	mListView = (ListView) findViewById(R.id.list_category);
		//	mCategoryAdapter = new CategoryAdapter(this, mCategoryList);
		//	mListView.setAdapter(mCategoryAdapter);
			
//			mListView.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view, int position,
//						long id) {
//					
//					Log.d("DERYL", "position >>>>>>>>>>>"+ position);
//					Intent intent = new Intent(MainActivity.this, ProductActivity.class);
//					intent.putExtra("position", position);
//					startActivity(intent);
//				}
//				
//			});
		}
		else
		{
			Log.d("DERYL", "PLEASE CHECK THIS CONDITION LENGHT ZERO IN CATEGORY LIST");
		}
		
	}

	public void gotoProduct(View v)
	{
		  switch (v.getId()) {
	        case R.id.cakes_button:
	        	Intent cakeintent = new Intent(MainActivity.this, ProductActivity.class);
	        	cakeintent.putExtra("position", 0);
				startActivity(cakeintent);
	            break;
	        case R.id.sweets_button:
	        	Intent sweetintent = new Intent(MainActivity.this, ProductActivity.class);
	        	sweetintent.putExtra("position", 1);
                startActivity(sweetintent);
	            break;
	        default:
	            break;
		  }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
}
