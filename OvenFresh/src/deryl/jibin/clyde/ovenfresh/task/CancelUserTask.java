package deryl.jibin.clyde.ovenfresh.task;

import java.util.HashMap;
import java.util.List;


import deryl.jibin.clyde.ovenfresh.CancelOrderActivity;
import deryl.jibin.clyde.ovenfresh.ErrorActivity;
import deryl.jibin.clyde.ovenfresh.adapter.CancelOrderAdapter;
import deryl.jibin.clyde.ovenfresh.adapter.ExpandableListAdapter;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrderList;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;

import Utils.JsonHelper;
import Utils.ServerInteraction;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class CancelUserTask extends AsyncTask<Void, Integer, Void>
{
	private Activity mContext;
	private DialogInterface mDialogInterface;
	private String orderID;
	ProgressDialog pd;
	ListView mListView;
	boolean isTaskSuccessfull = true;
	int userStatus;
	List<String> listDataHeader;
	HashMap<String, List<BookedOrder>> listDataChild;
	ExpandableListView expListView;
	
	public CancelUserTask(Activity mContext, DialogInterface mDialogInterface,
			String orderID, List<String> listDataHeader, ExpandableListView expListView) {
		super();
		this.mContext = mContext;
		this.mDialogInterface = mDialogInterface;
		this.orderID = orderID;
		this.listDataHeader = listDataHeader;
		this.expListView = expListView;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		pd = new ProgressDialog(mContext);
		pd.setTitle(Constants.progressMessageTitle);
		pd.setMessage(Constants.progressMessageCancel);
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		try
		{
			String data = ServerInteraction.getJson(Constants.requestcancelOrder+orderID);
			if(data != null)
			{
				userStatus = JsonHelper.getCancelOrder(data);
			}
			else
			{
				Log.d("DERYL", ">>> NO CUSTOMER DATA FOUND >>>>>>>>");
				isTaskSuccessfull = false;
			}
			return null;
		}
		catch (Exception e) {
			// TODO: handle exception
			isTaskSuccessfull = false;
			return null;
		} 
		
	}
		
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		 if(isTaskSuccessfull)
		 {
			 if(userStatus == 1)
			 {
				  if(BookedOrderList.getInstance() != null && BookedOrderList.getInstance().getBookedOrderList() != null && BookedOrderList.getInstance().getBookedOrderList().size() > 0)
				  {
					  List<BookedOrder> orderList = BookedOrderList.getInstance().getBookedOrderList();
					  List<BookedOrder> cancelList = BookedOrderList.getInstance().getCancelOrderList();
					  
					  for (BookedOrder temp : orderList) 
					  {
						if(temp.getOrderDetails().getOrderId().equals(orderID))
						{
							temp.getOrderDetails().setStatus(Constants.cancelStatus);
							orderList.remove(temp);
							cancelList.add(temp);
							break;
						}
					  }
					  BookedOrderList.getInstance().setBookedOrderList(orderList);
					  BookedOrderList.getInstance().setCancelOrderList(cancelList);
					//CancelOrderAdapter adapter = new CancelOrderAdapter(mContext, orderList);
					listDataChild = new HashMap<String, List<BookedOrder>>();
					listDataChild.put(listDataHeader.get(0), BookedOrderList.getInstance().getBookedOrderList()); 
	    			listDataChild.put(listDataHeader.get(1), BookedOrderList.getInstance().getReadyOrderList());
	    			listDataChild.put(listDataHeader.get(2), BookedOrderList.getInstance().getCompleteOrderList());
	    			listDataChild.put(listDataHeader.get(3), BookedOrderList.getInstance().getCancelOrderList());
	    			ExpandableListAdapter listAdapter = new ExpandableListAdapter(mContext, listDataHeader, listDataChild);
	  	        	expListView.setAdapter(listAdapter);
					mDialogInterface.cancel();
				  }
			 }
				 
			 if (pd!=null) {
					pd.dismiss();
				}	
				
		 }
		 else
		 {
			 Intent intent = new Intent(mContext, ErrorActivity.class);
			 mContext.startActivity(intent);
		 }
	
		
	}
}
