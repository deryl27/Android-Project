package deryl.jibin.clyde.ovenfresh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.internal.mj;

import deryl.jibin.clyde.ovenfresh.entity.BookedOrder;
import deryl.jibin.clyde.ovenfresh.entity.BookedOrderList;
import deryl.jibin.clyde.ovenfresh.entity.Cakes;
import deryl.jibin.clyde.ovenfresh.entity.Category;
import deryl.jibin.clyde.ovenfresh.entity.CategoryList;
import deryl.jibin.clyde.ovenfresh.entity.Order;
import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.Product;
import deryl.jibin.clyde.ovenfresh.entity.ProductList;

public class JsonHelper {
	
	public static void createCategoryList(String jsonResponse) throws Exception
	{
			JSONObject mJsonObject = new JSONObject(jsonResponse);
			
			 JSONArray categoryArray =  mJsonObject.getJSONArray(Constants.categoryArray);
			 
			 List<Category> mCategoryList = new ArrayList<Category>();
			 
			 for(int i = 0 ; i < categoryArray.length() ; i++)
			 {
				 JSONObject categoryObject = categoryArray.getJSONObject(i);
				 Category tempCategoryObject = new Category(categoryObject.getString(Constants.CATEGORYID), categoryObject.getString(Constants.CATEGORYNAME));
				 mCategoryList.add(tempCategoryObject);
				 
			 }
			 CategoryList.getInstance().setCategoryList(mCategoryList);
	}
	
	
	public static Integer getUpdateProfile(String jsonResponse) throws Exception
	{
		JSONObject mJsonObject = new JSONObject(jsonResponse);
		
		JSONArray customer = mJsonObject.getJSONArray(Constants.customerArray);
        String status = customer.getJSONObject(0).getString(Constants.CUSTOMERUSERSTATUS);		 
		 
		return Integer.parseInt(status);
	}
	
	
	public static void createProductList(String jsonResponse) throws Exception
	{
			 JSONObject mJsonObject = new JSONObject(jsonResponse);
			
			 JSONArray productArray =  mJsonObject.getJSONArray(Constants.productArray);
			 
			 List<Product> mCakeProductList = new ArrayList<Product>();
			 List<Product> mSweetProductList = new ArrayList<Product>();
			 
			 if(CategoryList.getInstance() != null && CategoryList.getInstance().getCategoryList().size() > 0)
			 { 
				 List<Category> mCategoryList = CategoryList.getInstance().getCategoryList();
				 for(int i = 0 ; i < productArray.length() ; i++)
				 {
					 JSONObject  productObject = productArray.getJSONObject(i);
					 
					 if(productObject.getString(Constants.PRODUCTCATEGORY).equals(mCategoryList.get(0).getCategoryID())) // for Cakes
					 {
						 Product cakeProduct = new Product(productObject.getString(Constants.PRODUCTID), productObject.getString(Constants.PRODUCTNAME), productObject.getDouble(Constants.PRODUCTPRICE), productObject.getString(Constants.PRODUCTCATEGORY));
						 mCakeProductList.add(cakeProduct);
					 }
					 else if(productObject.getString(Constants.PRODUCTCATEGORY).equals(mCategoryList.get(1).getCategoryID())) // for Sweets
					 {
						 Product sweetProduct = new Product(productObject.getString(Constants.PRODUCTID), productObject.getString(Constants.PRODUCTNAME), productObject.getDouble(Constants.PRODUCTPRICE), productObject.getString(Constants.PRODUCTCATEGORY));
						 mSweetProductList.add(sweetProduct);
					 }
				 
				 }
				 
				 HashMap<String, List<Product>> productHashMap = new HashMap<String, List<Product>>();
				 productHashMap.put(mCategoryList.get(0).getCategoryID(), mCakeProductList);
				 productHashMap.put(mCategoryList.get(1).getCategoryID(), mSweetProductList);
				 
				 ProductList.getInstance().setProductList(productHashMap);
			 }
		
	}
	
	public static void getOrderid(String jsonResponse) throws Exception
	{
			JSONObject mJsonObject = new JSONObject(jsonResponse);

			JSONArray myBookingListArray =  mJsonObject.getJSONArray(Constants.mybookingList);
			 List<BookedOrder> mPendingOrder = new ArrayList<BookedOrder>();
			 List<BookedOrder> mReadyOrder = new ArrayList<BookedOrder>();
			 List<BookedOrder> mCompleteOrder = new ArrayList<BookedOrder>();
			 List<BookedOrder> mCancelOrder = new ArrayList<BookedOrder>();
			 
			 Log.d("DERYL", "JSON RESPONSE "  +  jsonResponse);
			 
			 if(mJsonObject.getString(Constants.orderExist).equals(Constants.userExist))
			 {
				 Log.d("DERYL", ">> INSIDE IF CONDITION ");
				 Constants.orderID = mJsonObject.getString(Constants.bookingOrderId);
				 createMyBookingList(jsonResponse);
//				 
//				 for(int i = 0 ; i < myBookingListArray.length() ; i++)
//				 {
//					 
//					 JSONObject mybookingObject = myBookingListArray.getJSONObject(i);
//					 
//					 JSONArray productListArray = mybookingObject.getJSONArray(Constants.orderList);
//					 List<OrderProduct> orderList = new ArrayList<OrderProduct>();
//					 for(int j=0 ; j <  productListArray.length() ; j++)
//					 {
//						 JSONObject productObject = productListArray.getJSONObject(j);
//						 OrderProduct product = new OrderProduct("", productObject.getString(Constants.PRODUCTNAME) , "", productObject.getString(Constants.PRODUCTQUANTITY) , productObject.getString(Constants.PRODUCTITEMCOUNT), productObject.getString(Constants.PRODUCTACTUALPRICE),"");
//						 orderList.add(product);
//					 }
//					 
//					 String orderDeliveryDate = mybookingObject.getString(Constants.orderDate);
//					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					 Date date = null;
//					 try
//					 {
//     				  date = formatter.parse(orderDeliveryDate);
//					 }
//					 catch (Exception e) {
//					 }
//     				 Calendar cal = Calendar.getInstance();
//					 cal.setTime(date);
//					 String orderDate=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
//					 String time = cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE);
//					 if( cal.get(Calendar.AM_PM) == 1)
//					 {
//						 time+=" PM";
//					 }
//					 else
//					 {
//						 time+=" AM";
//					 }
//					 Order order = new Order(mybookingObject.getString(Constants.bookingOrderId), orderDate, mybookingObject.getDouble(Constants.orderPrice), mybookingObject.getString(Constants.orderStatus), time, mybookingObject.getString(Constants.orderGeneratedTime));
//					 if(BookedOrderList.getInstance()!=null && BookedOrderList.getInstance().getBookedOrderList() != null &&  BookedOrderList.getInstance().getBookedOrderList().size() > 0)
//					 {
//						 List<BookedOrder> mBookedOrders = BookedOrderList.getInstance().getBookedOrderList();
//						 BookedOrder pendingOrder = new BookedOrder(order, orderList);
//						 mBookedOrders.add(pendingOrder);
//						 BookedOrderList.getInstance().setBookedOrderList(mBookedOrders);
//					 }
//					 else
//					 {
//						 if(mybookingObject.getString(Constants.orderStatus).equals(Constants.pendingStatus))
//						 {
//							 BookedOrder pendingOrder = new BookedOrder(order, orderList);
//							 mPendingOrder.add(pendingOrder);
//						 }
//						 BookedOrderList.getInstance().setBookedOrderList(mPendingOrder);
//						 BookedOrderList.getInstance().setReadyOrderList(mReadyOrder);
//						 BookedOrderList.getInstance().setCompleteOrderList(mCompleteOrder);
//						 BookedOrderList.getInstance().setCancelOrderList(mCancelOrder);
//					 }
//				 }
//				 
//				
			 }
			 else
			 {
				 Log.d("DERYL", ">>>>>> ELSE CONDITION CASES ");
				 
			 }
			
			
			
			 
	}
	
	
	public static void getCustomerDetails(String jsonResponse) throws Exception
	{
			JSONObject mJsonObject = new JSONObject(jsonResponse);
			
			 JSONArray customerArray =  mJsonObject.getJSONArray(Constants.customerArray);
			 
			 for(int i = 0 ; i < customerArray.length() ; i++)
			 {
				 JSONObject customer = customerArray.getJSONObject(i);
				 
				 if(customer.getString(Constants.CUSTOMERUSERSTATUS).equals(Constants.userExist))
				 {
					 Log.d("DERYL", "USER EXIST");
					 Constants.custFName = customer.getString(Constants.CUSTOMERFIRSTNAME);
					 Constants.custLName = customer.getString(Constants.CUSTOMERLASTNAME);
					 Constants.custemail = customer.getString(Constants.CUSTOMEREMAILID);
					 Constants.custstatus = customer.getString(Constants.CUSTOMERUSERSTATUS);
				 }
				 else if(customer.getString(Constants.CUSTOMERUSERSTATUS).equals(Constants.userNotExist))
				 {
					 Log.d("DERYL", "USER NOT  EXIST");
					 Constants.custstatus = customer.getString(Constants.CUSTOMERUSERSTATUS);
				 }
				 
			 }
	}
	
	public static int getCancelOrder(String jsonResponse) throws Exception
	{
		
		 JSONObject mJsonObject = new JSONObject(jsonResponse);
		 
		 return mJsonObject.getInt(Constants.CUSTOMERUSERSTATUS);
		 
	}
	
	
	public static void createMyBookingList(String jsonResponse) throws Exception
	{
			
			 JSONObject mJsonObject = new JSONObject(jsonResponse);
			
			 JSONArray myBookingListArray =  mJsonObject.getJSONArray(Constants.mybookingList);
			 Log.d("DERYL", "JSONRESPONSE " + jsonResponse);
			 if(mJsonObject.getString(Constants.orderExist).equals(Constants.userExist))
			 {
				 Log.d("DERYL", ">> INSIDE IF CONDITION ");
				 List<BookedOrder> mPendingOrder = new ArrayList<BookedOrder>();
				 List<BookedOrder> mCompleteOrder = new ArrayList<BookedOrder>();
				 List<BookedOrder> mReadyOrder = new ArrayList<BookedOrder>();
				 List<BookedOrder> mCancelledOrder = new ArrayList<BookedOrder>();
				 for(int i = 0 ; i < myBookingListArray.length() ; i++)
				 {
					 
					 JSONObject mybookingObject = myBookingListArray.getJSONObject(i);
					 
					 JSONArray productListArray = mybookingObject.getJSONArray(Constants.orderList);
					 List<OrderProduct> orderList = new ArrayList<OrderProduct>();
					 for(int j=0 ; j <  productListArray.length() ; j++)
					 {
						 JSONObject productObject = productListArray.getJSONObject(j);
						 OrderProduct product = new OrderProduct("", productObject.getString(Constants.PRODUCTNAME) , "", productObject.getString(Constants.PRODUCTQUANTITY) , productObject.getString(Constants.PRODUCTITEMCOUNT), productObject.getString(Constants.PRODUCTACTUALPRICE),"");
						 orderList.add(product);
					 }
					 
					 String orderDeliveryDate = mybookingObject.getString(Constants.orderDate);
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 Date date = null;
					 try
					 {
     				  date = formatter.parse(orderDeliveryDate);
					 }
					 catch (Exception e) {
					 }
     				 Calendar cal = Calendar.getInstance();
					 cal.setTime(date);
					 String orderDate=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
					 String time = cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE);
					 if( cal.get(Calendar.AM_PM) == 1)
					 {
						 time+=" PM";
					 }
					 else
					 {
						 time+=" AM";
					 }
					 Order order = new Order(mybookingObject.getString(Constants.bookingOrderId), orderDate, mybookingObject.getDouble(Constants.orderPrice), mybookingObject.getString(Constants.orderStatus), time , mybookingObject.getString(Constants.orderGeneratedTime));
					 
					 if(mybookingObject.getString(Constants.orderStatus).equals(Constants.pendingStatus))
					 {
						 BookedOrder pendingOrder = new BookedOrder(order, orderList);
						 mPendingOrder.add(pendingOrder);
					 }
					 else if(mybookingObject.getString(Constants.orderStatus).equals(Constants.readyStatus))
					 {
						 Log.d("DERYL", "READY ORDER LIST");
						 BookedOrder readyOrder = new BookedOrder(order, orderList);
						 mReadyOrder.add(readyOrder);
					 }
					 else if(mybookingObject.getString(Constants.orderStatus).equals(Constants.completeStatus))
					 {
						 Log.d("DERYL", "COMPLETE ORDER LIST");
						 BookedOrder readyOrder = new BookedOrder(order, orderList);
						 mCompleteOrder.add(readyOrder);
					 }
					 else if (mybookingObject.getString(Constants.orderStatus).equals(Constants.cancelStatus))
					 {
						 Log.d("DERYL", "CANCELLED ORDER LIST");
						 BookedOrder readyOrder = new BookedOrder(order, orderList);
						 mCancelledOrder.add(readyOrder);
					 }
				 }
				 
				 BookedOrderList.getInstance().setBookedOrderList(mPendingOrder);
				 BookedOrderList.getInstance().setCompleteOrderList(mCompleteOrder);
				 BookedOrderList.getInstance().setReadyOrderList(mReadyOrder);
				 BookedOrderList.getInstance().setCancelOrderList(mCancelledOrder);
			 }
			 else
			 {
				 
			 }
		
	}
	

}
