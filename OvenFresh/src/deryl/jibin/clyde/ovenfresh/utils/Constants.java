package deryl.jibin.clyde.ovenfresh.utils;

import java.util.ArrayList;
import java.util.List;


public class Constants {
	
	public static String productName = "PRODUCT_NAME";
	public static String productPrice = "PRODUCT_PRICE";
	public static String productID = "PRODUCT_ID";
	public static String productCAT = "PRODUCT_CAT";
	
	public static String select = "Select";
	public static String noQuantityError = "Please Select Valid Quantity";
	public static String cart_error ="No Product Present in Cart";
	
	public static String existingUser = "Existing User";
	public static String newUser = "New User !!! Please enter your details";
	public static String invalidmobileNumber = "Invalid Mobile Number";
	public static String progressMessage = "Peforming User Check !!!!";
	public static String submitOrderMessage = "Fetching OrderId !!!!";
	public static String progressMessageTitle = "Processing...";
	public static String updateMessageTitle = "Updating Profile...";
	public static String progressMessageCancel = "Cancelling Order...";
	public static String updateMessage = "Updating your information Please Wait...";
	public static String progressRefreshMessage = "Processing All Orders... !!!!";
	public static String RefershingTitle = "Fetching Orders !!!!";
	public static String LoadingContentTitle = "Loading Content...";
	public static String LoadingContentMessage = "Initializing Content...";
	
	public static String addToCartSuccess = "Product Added to Cart Successfully";
	public static String cartCount = "CartCount";
	
	public static String mybooking = "My Booking";
	public static String cancelorder = "Cancel Order";
	public static String profile = "Profile";
	public static String contactus = "Contact Us";
	public static String menuactivity = "MenuActivity";
	public static String reportactivity = "ReportActivity";
	
	public static String orderid = "ORDERID";
	public static String totalsum = "TOTALSUM";
	public static String status = "STATUS";
	
	public static String alertMessageYes = "Yes";
	public static String alertMessageNo = "No";
	public static String alertMessageOk = "OK";
	
	
	// ERROR TEXT 
	public static String connectivityError = "There is some problem in connectivity. Please Try Again Later.";	
	public static String noBookingMessage = "NO CURRENT OR PAST BOOKING ";
	
	public static String alertNoInternet="Internet is disabled in your device. Would you like to enable it?";
	public static String positiveButtonMessage="Enable Internet";
	public static String cancel = "Cancel";
	public static String alertCancelOrderMessage = "Are You Sure you want to cancel the selected order";
	public static String alertRemoveOrderMessage = "Are You Sure you want to remove the selected item from the cart";
	public static String editPostion = "EDITPOSTION";
	public static String activity = "Activity";
	public static String customerErrorMessage = "Some Fields are Blank. Please Enter Valid Inputs";
	public static String customerEmailErrorMessage = "Please Enter Valid Email Address";
	public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	
	
	/* --------------------------------------------- ANNOTATION  FOR JSON PARSING-----------------------------------------------         */
	
    // CATEGORY 
	public static String CATEGORYID = "categoryID";
	public static String CATEGORYNAME = "categoryName";
	
	// PRODUCT
	public static String PRODUCTID = "productID";
	public static String PRODUCTNAME = "productName";
	public static String PRODUCTPRICE = "productPrice";
	public static String PRODUCTSTATUS = "productStatus";
	public static String PRODUCTCATEGORY = "productCategory";
	public static String PRODUCTQUANTITY = "productQuantity";
	public static String PRODUCTITEMCOUNT = "productItemCount";
	public static String PRODUCTACTUALPRICE = "productActualPrice";

	// CUSTOMER
	public static String CUSTOMERFIRSTNAME = "firstName";
	public static String CUSTOMERLASTNAME = "lastName";
	public static String CUSTOMERMOBILENO = "mobileNo";
	public static String CUSTOMEREMAILID = "emailid";
	public static String CUSTOMERUSERSTATUS = "userStatus";
	
	public static String custFName  ="";
	public static String custLName  ="";
	public static String custemail  ="";
	public static String custstatus ="";
	public static String orderID = "";

	public static String userExist = "EXIST";
	public static String userNotExist = "NOTEXIST";
	
	
	public static String orderExist = "orderExist";
	public static String orderStatus = "orderStatus";
	public static String orderDate = "orderDate";
	public static String bookingOrderId = "orderID";
	public static String orderGeneratedTime = "orderGeneratedDate";
	public static String orderPrice = "totalPrice";
	
	public static String pendingStatus = "PENDING";
	public static String readyStatus = "READY";
	public static String completeStatus = "COMPLETE";
	public static String cancelStatus = "CANCELLED";
	
	// SAVE MOBILE NUMBER
	
	public static String SharedPreferenceName ="MobilePrefernces";
	public static String MobileNumberTag ="mobile";
	public static String TagFirstName ="first";
	public static String TagLastName ="last";
	public static String TagEmail ="email";
	
	
	/*---------------------------------- URL FOR ACCESSING SERVER ----------------------------------*/
	public static String ipAddress ="54.86.5.58";
	public static String portNo ="8080";
	public static String getProductList ="http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/getProductListAndroid";
	public static String getCategoryList ="http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/getCategoryList";
	public static String getCustomerData ="http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/getCustomerData?mobileNo=";
	public static String putOrderData ="http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/getOrderDetails?orderDetails=";
	public static String getBookingData ="http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/getBookingDetails?mobileNo=";
	public static String requestcancelOrder ="http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/requestOrderCancel?orderID=";
	public static String registeration = "http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/getRegID?mobileNo=";
	public static String updateProfie = "http://"+ipAddress+":"+portNo+"/FreshOvenApp/webresources/service/updateUserProfile?newNo=";
			
		
	
	/*------------------------------------  JSON TAG  -------------------------------------------------------*/
	
	public static String categoryArray = "category";
	public static String productArray = "product";
	public static String customerArray = "customer";
	public static String mybookingList = "mybookinglist";
	public static String orderList = "orderList";
	public static String orderId = "orderId";
	
	
	/*---------------------------------------- GCM NOTIFICATION SERVER ----------------------------------*/
	
	public static  String projectNumber = "715201551539";
	public static  String REGID = "";
	public static  String REGIDConstant = "REGID";
	public static  String ERORR = "Please Enter Valid 10 Digit Number";
	public static  boolean isRegActivity = false;
	public static  boolean isFromNotification = false;
	
	
	public static  String shopCakes = "Shop Cakes";
	public static  String shopSweet = "Shop Sweets";
	
	public static  String emailAddres ="ovenfreshreview@gmail.com";
	public static  String isLoad = "isLoad";
	public static  String mumbaiorders ="Place order for Mumbai Only !!!!!";
	public static  String latermessage = "Please ";
	public static  String newnum = "10 Digit New Number";
	public static  String updateProfile = "Profile Updated Successfully";
	

	
}
