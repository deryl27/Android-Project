package deryl.jibin.clyde.ovenfresh.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

public class Utility {
	
	public static boolean isPresentINVIEWORDER = false;
    public static SharedPreferences sharedPrefence = null;	
	public static double calculatePrice(double price,int quantity, int item)
	{
		double actualprice = 0 ;
		double temp =0;
		switch (quantity) {
		case 1:
	            	actualprice = price * item;	   
			
			break;

		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
					actualprice = price * item * quantity;
			break;
			
		case 250:
					temp = Math.round(price/4);
					actualprice = temp * item; 
			break;
			
		case 500: 
				    temp = Math.round(price/2);
				    actualprice = temp * item;
			
			break;
			
		default:
			break;
		}
		return actualprice;
	}
	
	
	public static void setCartCount(Activity mActivity)
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
		
		final TextView addtoCartTextView = (TextView) mActivity.findViewById(R.id.cart_number);
		addtoCartTextView.setText(count+"");
	}
	
	
	 public static boolean isOnline(Activity context) 
	 {
		    ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		        return true;
		    }
		    return false;
	  }
	 
	 public static void showWIFIDisabledAlertToUser(final Activity context)
	 {
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		 alertDialogBuilder.setMessage(Constants.alertNoInternet).setCancelable(false)
		 .setPositiveButton(Constants.positiveButtonMessage,
		 new DialogInterface.OnClickListener(){
		 public void onClick(DialogInterface dialog, int id){
		 Intent intent=new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
		 context.startActivity(intent);
		 }
		 });
		 alertDialogBuilder.setNegativeButton(Constants.cancel,
		 new DialogInterface.OnClickListener(){
		 public void onClick(DialogInterface dialog, int id){
		 dialog.cancel();
		 context.finish();
		 
		 }
		 });
		 
		 AlertDialog alert = alertDialogBuilder.create();
		 alert.show();
		 
		 
	 }
	 
	



}
