package deryl.jibin.clyde.ovenfresh;

import java.util.ArrayList;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class QuantityActivity extends ParentActivity {
	TextView productNameTextView, productPriceTextView, actualpriceTextView;
	Spinner quantity,item;
	int position = -1;
	boolean isFromOrderActivity = false;
	String productId, productCat="";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.quantity_activity);
	    super.onCreate(savedInstanceState);
	    
	    String productName ="", productPrice = "";
	    Button cartButton = (Button) findViewById(R.id.addToCartButton);
	    if( getIntent().getStringExtra(Constants.activity) != null && getIntent().getStringExtra(Constants.activity).equals("OrderActivity"))
	    {
	    	position = getIntent().getIntExtra(Constants.editPostion, -1);
	    	if(OrderProductList.getInstance().getOrderList() != null)
	    	{
	    		productId = OrderProductList.getInstance().getOrderList().get(position).getProductId();
	    		productName = OrderProductList.getInstance().getOrderList().get(position).getProductName();
	    		productPrice = OrderProductList.getInstance().getOrderList().get(position).getProductPrice();
	    		productCat = OrderProductList.getInstance().getOrderList().get(position).getProductCategory();
	    		cartButton.setText(R.string.updateCart);
	    		isFromOrderActivity = true;
	    	}
	    	
	    }
	    else
	    {
	    	Bundle productBundle = getIntent().getExtras();
	    	productId = productBundle.getString(Constants.productID);
	    	productName = productBundle.getString(Constants.productName);
	    	productPrice = productBundle.getString(Constants.productPrice);
	    	productCat = productBundle.getString(Constants.productCAT);
	    	isFromOrderActivity = false;
	    }
	    
	    productNameTextView = (TextView) findViewById(R.id.quantity_name);
	    productPriceTextView = (TextView) findViewById(R.id.quantity_price);
	    actualpriceTextView = (TextView) findViewById(R.id.actual_price);
	    
	    quantity = (Spinner)findViewById(R.id.spinner_quantity);
	    item = (Spinner) findViewById(R.id.spinner_item);
	    
     
	    ArrayAdapter<String> dataAdapter = null;
	    if(productCat.equals("C001"))
	    {
    	dataAdapter = new ArrayAdapter<String>(this,
	    		android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cakequantity));
	    }
	    else if(productCat.equals("C002"))
	    {
	    	dataAdapter = new ArrayAdapter<String>(this,
		    		android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.sweetquantity));
	    }
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    quantity.setAdapter(dataAdapter);
	    
	    ArrayAdapter<String> dataAdapteritem = new ArrayAdapter<String>(this,
	    		android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.items));
	    dataAdapteritem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    item.setAdapter(dataAdapteritem);
    	
	    
	    quantity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				
				if(quantity.getSelectedItem().toString().equals(Constants.select))
				{
					//Toast.makeText(QuantityActivity.this,Constants.noQuantityError, Toast.LENGTH_SHORT).show();
					actualpriceTextView.setText("");
				}
				else
				{
					double actualPrice = calculatePrice(quantity.getSelectedItem().toString(), Integer.parseInt(item.getSelectedItem().toString()));
					actualpriceTextView.setText(actualPrice+"");
				}
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    item.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				if(quantity.getSelectedItem().toString().equals(Constants.select))
				{
					//Toast.makeText(QuantityActivity.this,Constants.noQuantityError, Toast.LENGTH_SHORT).show();
					actualpriceTextView.setText("");
				}
				else
				{
					double actualPrice = calculatePrice(quantity.getSelectedItem().toString(), Integer.parseInt(item.getSelectedItem().toString()));
					actualpriceTextView.setText(actualPrice+"");
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
		});
	    productNameTextView.setText(productName);
	    productPriceTextView.setText(productPrice);
	    
	}
	
	public double calculatePrice(String quantity, int item)
	{
		Log.d("DERYL", ">>>>>>> CALCULATE PRICE >>>>>>>");
		int qty = Integer.parseInt(quantity.split(" ")[0]);
		String productPrice = productPriceTextView.getText().toString().split("/")[0].split(" ")[1];
		Log.d("DERYL", "qty>>"+ qty +"prodd>>>"+ productPrice);
		double price = Double.parseDouble(productPrice);
		return Utility.calculatePrice(price,qty,item);
		
	}
	
	public void  addToCart(View v)
	{
		Log.d("DERYL",">>> ADD TO CART ");
		if(quantity.getSelectedItem().toString().equals(Constants.select))
		{
			Toast.makeText(QuantityActivity.this,Constants.noQuantityError, Toast.LENGTH_SHORT).show();
			actualpriceTextView.setText("");
		}
		else
		{
			String productName = productNameTextView.getText().toString();
			String productPrice = productPriceTextView.getText().toString();
			
			String productQuantity = quantity.getSelectedItem().toString();
			String productItem = item.getSelectedItem().toString();
			String productActualPrice = actualpriceTextView.getText().toString();
	        OrderProduct mOrderProduct = new OrderProduct(productId,productName, productPrice, productQuantity, productItem, productActualPrice, productCat);
	        
	        if(isFromOrderActivity)
	        {
	        	
	        	
	        	 if( OrderProductList.getInstance().getOrderList() != null )
			     {
	        		 OrderProductList.getInstance().getOrderList().get(position).setProductName(productId);
	        		 OrderProductList.getInstance().getOrderList().get(position).setProductName(productName);
	        		 OrderProductList.getInstance().getOrderList().get(position).setProductPrice(productPrice);
	        		 OrderProductList.getInstance().getOrderList().get(position).setProductQuantity(productQuantity);
	        		 OrderProductList.getInstance().getOrderList().get(position).setProductItemCount(productItem);
	        		 OrderProductList.getInstance().getOrderList().get(position).setProductActualPrice(productActualPrice);
			        	
			     }
	        	 
	        	 
	        }
	        else
	        {
		        if( OrderProductList.getInstance().getOrderList() == null )
		        {
		        	Log.d("DERYL", "INSIDE IF >>>>>>>");
		        	List<OrderProduct> mOrderProductLists = new ArrayList<OrderProduct>();
		        	mOrderProductLists.add(mOrderProduct);
		        	OrderProductList.getInstance().setOrderList(mOrderProductLists);
		        	
		        }
		        else
		        {
		        	Log.d("DERYL", "INSIDE ELSE >>>>>>>");
		        	OrderProductList.getInstance().getOrderList().add(mOrderProduct);
		        }
		        int count = OrderProductList.getInstance().getOrderList().size();
		        final TextView addtoCartTextView = (TextView) this.findViewById(R.id.cart_number);
				addtoCartTextView.setText(count+"");
	        }
	     //   Utility.setSharePreferenceValue(QuantityActivity.this);
	        
	        Toast.makeText(QuantityActivity.this, Constants.addToCartSuccess, Toast.LENGTH_SHORT).show();
		}  
        
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		isFromOrderActivity = false;
	}
	
	

}
