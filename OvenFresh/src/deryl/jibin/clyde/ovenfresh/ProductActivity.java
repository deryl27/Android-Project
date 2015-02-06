package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.adapter.LazyAdapter;
import deryl.jibin.clyde.ovenfresh.entity.Cakes;
import deryl.jibin.clyde.ovenfresh.entity.CategoryList;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.entity.Product;
import deryl.jibin.clyde.ovenfresh.entity.ProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends ParentActivity {

	ListView mListView;
	LazyAdapter mLazyAdapter;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.activity_main);
	    super.onCreate(savedInstanceState);
	    
	    
	    int position = getIntent().getIntExtra("position", 0);
	    List<Product> mProductList = new ArrayList<Product>();
	    if(ProductList.getInstance() != null && ProductList.getInstance().getProductList().size() > 0)
	    {
	    	
		    switch(position)
		    {
		    	case 0:
		               mProductList = ProductList.getInstance().getProductList().get(CategoryList.getInstance().getCategoryList().get(0).getCategoryID());	   
		    		break;
		    	case 1:
		    		mProductList = ProductList.getInstance().getProductList().get(CategoryList.getInstance().getCategoryList().get(1).getCategoryID());
		    		break;
		    }
			mListView = (ListView) findViewById(R.id.list);
			mLazyAdapter = new LazyAdapter(this, mProductList);
			
			mListView.setAdapter(mLazyAdapter);
	    }
	    else
	    {
	    	Log.d("DERYL", "PLEASE CHECK THIS CONDITION LENGHT ZERO IN PRODUCT LIST");
	    }
	}
	
	public void  addProductToCart(View v)
	{
		Log.d("DERYL",">>> ADD TO CART ");
		LinearLayout productDescription = (LinearLayout)v.getParent();
        
		//LinearLayout productDescription = (LinearLayout)vwParentRow.getChildAt(1);
		
        TextView productNameTextView = (TextView) productDescription.getChildAt(0);
        TextView productPriceTextView = (TextView) ((LinearLayout)productDescription.getChildAt(1)).getChildAt(0);
        TextView productIdTextView = (TextView) ((LinearLayout)productDescription.getChildAt(1)).getChildAt(1);
        TextView productCategoryTextView = (TextView) ((LinearLayout)productDescription.getChildAt(1)).getChildAt(2);
        Intent intent = new Intent(ProductActivity.this,QuantityActivity.class);
        Bundle productBundle = new Bundle();
        productBundle.putString(Constants.productName, productNameTextView.getText().toString());
        productBundle.putString(Constants.productPrice, productPriceTextView.getText().toString());
        productBundle.putString(Constants.productID, productIdTextView.getText().toString());
        productBundle.putString(Constants.productCAT, productCategoryTextView.getText().toString());
        intent.putExtras(productBundle);     
        startActivity(intent);
	}
	
   	
   	
	

}
