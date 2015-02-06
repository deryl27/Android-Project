package deryl.jibin.clyde.ovenfresh;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


import deryl.jibin.clyde.ovenfresh.entity.OrderProduct;
import deryl.jibin.clyde.ovenfresh.entity.OrderProductList;
import deryl.jibin.clyde.ovenfresh.task.CheckUserTask;
import deryl.jibin.clyde.ovenfresh.task.SubmitOrderTask;
import deryl.jibin.clyde.ovenfresh.utils.Constants;
import deryl.jibin.clyde.ovenfresh.utils.Utility;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CustomerActivity extends ParentActivity {
	LinearLayout customerInfo, mobilelLayout, detail_info, mobile_info;
	EditText mobile, first , last, email, date, time;
	TextView message, welcomeMessage , email_TextView, mobile_TextView;
	Button checkuserExist,submitDetails;
	String orderDate="";
	String mobileNo;
	/** Called when the activity is first created. */
	ImageView bSelectDate, bSelectTime;
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;

	public int year,month,day,hour,minute;
	
	private int mYear, mMonth, mDay,mHour,mMinute; 
	
	Button btnUp, btnDown, btnMinUp, btnMinDown, btnSet, btnCancel;
	TextView textViewUp, textViewMid, textViewBottom, textView4, textView5, textView6;
	 
	 int nStart = 8;
	 int nEnd = 22;

	 int minStart = 00;
	 int minEnd = 60;

	 
	public CustomerActivity() {
		
		final Calendar instance = Calendar.getInstance();
		mYear = instance.get(Calendar.YEAR);
		mMonth = instance.get(Calendar.MONTH);
		mDay = instance.get(Calendar.DAY_OF_MONTH);
		mHour = instance.get(Calendar.HOUR_OF_DAY);
		mMinute = instance.get(Calendar.MINUTE);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.customer_details);
	    super.onCreate(savedInstanceState);
	    
	    customerInfo = (LinearLayout) findViewById(R.id.customer_info);
	    customerInfo.setVisibility(View.GONE); 
	    mobile = (EditText) findViewById(R.id.mobile_number);
	    first = (EditText) findViewById(R.id.first_name);
	    last = (EditText) findViewById(R.id.last_name);
	    email = (EditText) findViewById(R.id.email);
	    message = (TextView) findViewById(R.id.message);
	    date = (EditText) findViewById(R.id.select_date);
	    checkuserExist = (Button) findViewById(R.id.check_exist);
	    time = (EditText) findViewById(R.id.select_time);
	    bSelectDate =(ImageView) findViewById(R.id.buttonSelectDate);
        bSelectTime =(ImageView) findViewById(R.id.buttonSelectTime);
        submitDetails = (Button) findViewById(R.id.submit_details);
        welcomeMessage = (TextView) findViewById(R.id.user_welcome);
        mobilelLayout = (LinearLayout) findViewById(R.id.mobileLayout);
        detail_info = (LinearLayout) findViewById(R.id.details_info);
        mobile_info = (LinearLayout) findViewById(R.id.mobile_info);
        email_TextView = (TextView) findViewById(R.id.email_TextView);
        mobile_TextView = (TextView) findViewById(R.id.mobile_TextView);
    	
        if(Utility.sharedPrefence == null)
		{
			 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
		}
    	if (Utility.sharedPrefence.contains(Constants.MobileNumberTag) && Utility.sharedPrefence.contains(Constants.TagLastName) && Utility.sharedPrefence.contains(Constants.TagFirstName)&& Utility.sharedPrefence.contains(Constants.TagEmail))
        {
    	   Log.d("DERYL", "INSIDE IF");
    	   mobileNo = Utility.sharedPrefence.getString(Constants.MobileNumberTag, "");
           mobile.setText(Utility.sharedPrefence.getString(Constants.MobileNumberTag, ""));
           first.setText(Utility.sharedPrefence.getString(Constants.TagFirstName, ""));
           last.setText(Utility.sharedPrefence.getString(Constants.TagLastName, ""));
           email.setText(Utility.sharedPrefence.getString(Constants.TagEmail, ""));
           Constants.custstatus = "EXIST";
           mobile_TextView.setText(Utility.sharedPrefence.getString(Constants.MobileNumberTag, ""));
           email_TextView.setText(Utility.sharedPrefence.getString(Constants.TagEmail, ""));
           
           mobilelLayout.setVisibility(View.GONE);
           welcomeMessage.setVisibility(View.VISIBLE);
           welcomeMessage.setText("Welcome Back " +Utility.sharedPrefence.getString(Constants.TagFirstName, ""));
           customerInfo.setVisibility(View.VISIBLE);
           detail_info.setVisibility(View.GONE);
           mobile_info.setVisibility(View.VISIBLE);
        }
    	else if (Utility.sharedPrefence.contains(Constants.MobileNumberTag))
    	{
    		mobileNo = Utility.sharedPrefence.getString(Constants.MobileNumberTag, "");
    		Log.d("DERYL", "INSIDE ELSE ");
    		// mobilelLayout.setVisibility(View.VISIBLE);
            // welcomeMessage.setVisibility(View.GONE);
            // customerInfo.setVisibility(View.GONE);
             
            // detail_info.setVisibility(View.VISIBLE);
            //mobile_info.setVisibility(View.GONE);
    		customerInfo.setVisibility(View.VISIBLE);
    	    welcomeMessage.setVisibility(View.GONE);
    		mobilelLayout.setVisibility(View.GONE);
    		mobile_info.setVisibility(View.GONE);
    		new CheckUserTask(CustomerActivity.this,first,last,email,customerInfo,message,checkuserExist).execute(Utility.sharedPrefence.getString(Constants.MobileNumberTag, ""));
    	}
        
        bSelectDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
        
        bSelectTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Submit(v);
			}
		});
	    
	}
	
	
	public void Submit(View v)
	{

		final Dialog dialog = new Dialog(CustomerActivity.this);
		dialog.setContentView(R.layout.custom_timepicker);
		
		DisplayMetrics dm = new DisplayMetrics(); 
		getWindowManager().getDefaultDisplay().getMetrics(dm); 
		int width = dm.widthPixels;
		int height = dm.heightPixels;

	    Log.i("DERYL",">>>>>>width"+ width+"    height"+ height);
	    dialog.getWindow().setLayout((6*width)/7,(4*height)/5);
		dialog.setTitle("Select Time");
		
		btnUp = (Button) dialog.findViewById(R.id.button1);
		btnDown = (Button) dialog.findViewById(R.id.buttonDown);
		btnMinUp = (Button) dialog.findViewById(R.id.button3);
		btnMinDown = (Button) dialog.findViewById(R.id.button4);
		btnSet = (Button) dialog.findViewById(R.id.setTimeButton);
		btnCancel = (Button) dialog.findViewById(R.id.cancelTimeButton);
		
	    textViewUp = (TextView) dialog.findViewById(R.id.textView1);
	    textViewMid = (TextView) dialog.findViewById(R.id.textView2);
	    textViewBottom = (TextView) dialog.findViewById(R.id.textView3);
	        
	    textView4 = (TextView) dialog.findViewById(R.id.textView4);
	    textView5 = (TextView) dialog.findViewById(R.id.textView5);
	    textView6 = (TextView) dialog.findViewById(R.id.textView6);
	       
	     textViewUp.setText("9");
	     textViewMid.setText("10");
	     textViewBottom.setText("11");
	        
	     textView4.setText("00");
	     textView5.setText("15");
	     textView6.setText("30");
	     
	     
	     btnUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onHourClick(v);
			}
		});
	     
	     btnDown.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					onHourClick(v);
				}
			});
	     
	     
	     btnMinUp.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				onMinClick(v);
			}
		});
	     
	     btnMinDown.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					onMinClick(v);
				}
			});
		 
	    
	     btnSet.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String selectTime = textViewMid.getText() + ":" + textView5.getText();
					time.setText(selectTime);
					dialog.cancel();
				}
			});
	     
	     btnCancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.cancel();
				}
			});
	     
		dialog.show();
		
	}
	
	
	  public void onHourClick(View v) {
		     String getString = String.valueOf(textViewMid.getText());
		     int curent = Integer.parseInt(getString);
		     if (v == btnUp) {
		    	 if (curent < nEnd) {
		             curent++;
		             textViewUp.setText(String.valueOf(curent - 1));
		             textViewMid.setText(String.valueOf(curent));
		             textViewBottom.setText(String.valueOf(curent + 1));
		            }
		     }
		     if (v == btnDown) {
		      if (curent > nStart) {
		       curent--;
		       textViewUp.setText(String.valueOf(curent - 1));
		       textViewMid.setText(String.valueOf(curent));
		       textViewBottom.setText(String.valueOf(curent + 1));
		      }
		     }
		    }
		    
		    
		    public void onMinClick(View v) {
		        String getString = String.valueOf(textView5.getText());
		        int curent = Integer.parseInt(getString);
		        if (v == btnMinUp) {
		         if ((curent+ 15) < minEnd) {
		             curent+=15;
		             textView4.setText(String.valueOf(curent - 15));
		             textView5.setText(String.valueOf(curent));
		             if((curent+15) == minEnd)
		             {
		          	   textView6.setText("00");
		             }
		             else
		             {
		          	   textView6.setText(String.valueOf(curent + 15));
		             }
		           }
		        }
		        if (v == btnMinDown) {
		         if (curent > minStart) {
		          curent-=15;
		          if((curent -15) < 0)
		          {
		        	  textView4.setText("45");
		        	  textView5.setText("00");
		          }
		          else
		          {
		        	  textView4.setText(String.valueOf(curent - 15));
		        	  textView5.setText(String.valueOf(curent));
		          }
		          textView6.setText(String.valueOf(curent + 15));
		         }
		        }
		       }
	
	
	
	
	
	public void checkUserExist(View v)
	{
		if(mobile.length() == 10)
		{	
			if(Utility.sharedPrefence == null)
			{
				 Utility.sharedPrefence = getSharedPreferences(Constants.SharedPreferenceName, Context.MODE_PRIVATE);
			}
			Editor editor = Utility.sharedPrefence.edit();
     	    editor.putString(Constants.MobileNumberTag, mobile.getText().toString());
		    editor.commit(); 
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(mobile.getWindowToken(), 0);
			new CheckUserTask(CustomerActivity.this,first,last,email,customerInfo,message,checkuserExist).execute(mobile.getText().toString());
		}
		else
		{
			message.setText(Constants.invalidmobileNumber);
			message.setVisibility(View.VISIBLE);
			customerInfo.setVisibility(View.GONE);
		}
	}
	
	public void openTermsCondition(View v)
	{
		Log.d("DERYL", "TERMS AND CONDTIONS");
		Intent intent = new Intent(CustomerActivity.this, TermsConditionsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);
	}
	
	public void submitOrder(View v)
	{
		
		Log.d("DERYL","productid >>>" + OrderProductList.getInstance().getOrderList().get(0).getProductId());
		if(first.getText().toString().trim().equals("") || last.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") || date.getText().toString().trim().equals("") || time.getText().toString().trim().equals("") )
		{
		  Toast.makeText(CustomerActivity.this, Constants.customerErrorMessage, Toast.LENGTH_SHORT).show();	
		}
		else
		{
			if(!email.getText().toString().matches(Constants.emailPattern))
			{
				Toast.makeText(CustomerActivity.this, Constants.customerEmailErrorMessage, Toast.LENGTH_SHORT).show();
			}
			else
			{
				if(OrderProductList.getInstance() != null  && OrderProductList.getInstance().getOrderList().size() > 0)
				{
					List<OrderProduct> mOrderProductList = OrderProductList.getInstance().getOrderList();
					String orderDetails="";
					for(int i=0; i<mOrderProductList.size(); i++)
					{
						OrderProduct product = mOrderProductList.get(i);
						
						orderDetails+=product.getProductId()+",";
						orderDetails+=product.getProductQuantity().split(" ")[0].toString()+",";
						orderDetails+=(int)(Double.parseDouble(product.getProductActualPrice()))+",";
						if((i+1)<mOrderProductList.size())
						{
							orderDetails+=product.getProductItemCount()+"_";
						}
						else
						{
							orderDetails+=product.getProductItemCount();
						}
					}
					
					Log.d("DERYL", "orderDetails +=" +orderDetails);
					new SubmitOrderTask(CustomerActivity.this, orderDetails, first.getText().toString(), last.getText().toString(), email.getText().toString(), orderDate, time.getText().toString()+":00",mobileNo , Constants.custstatus, submitDetails).execute();
					
				}
				
			
			}
		}
	}
	
	
	// Register  DatePickerDialog listener
    private DatePickerDialog.OnDateSetListener mDateSetListener =
                           new DatePickerDialog.OnDateSetListener() {
                               public void onDateSet(DatePicker view, int yearSelected,
                                                     int monthOfYear, int dayOfMonth) {
                                  year = yearSelected;
                                  String monthFormat = (monthOfYear+1)+"";
                                  day = dayOfMonth;
                                  String dayFormat = dayOfMonth +"";
                                  if(monthFormat.length() == 1)
                                  {
                                	  monthFormat = "0" + monthFormat;
                                  }
                                  if(dayFormat.length() == 1)
                                  {
                                	  dayFormat = "0" + dayFormat;
                                  }
                                  orderDate = year+"-"+monthFormat+"-"+dayFormat;
                                  date.setText(dayFormat+"/"+monthFormat+"/"+year);
                                  date.setVisibility(View.VISIBLE);
                               }

                           };

      // Register  TimePickerDialog listener                 
                           private TimePickerDialog.OnTimeSetListener mTimeSetListener =
                               new TimePickerDialog.OnTimeSetListener() {
                                   public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                                       hour = hourOfDay;
                                       minute = min;
                                       time.setText(hour+"-"+minute);
                                       time.setVisibility(View.VISIBLE);
                                     }
                               };


   // Method automatically gets Called when you call showDialog()  method
                           @Override
                           protected Dialog onCreateDialog(int id) {
                               switch (id) {
                               case DATE_DIALOG_ID:
                        // create a new DatePickerDialog with values you want to show 
                                   DatePickerDialog mDatePickerDialog =  new DatePickerDialog(this,
                                               mDateSetListener,
                                               mYear, mMonth, mDay);
                                   DatePicker dp = mDatePickerDialog.getDatePicker(); 
//                                   Calendar  c = Calendar.getInstance();
//                                   c.set(Calendar.HOUR_OF_DAY, 0);
//                                   dp.setMinDate(c.getTimeInMillis());
                                   Calendar minDate = Calendar.getInstance();
                                   minDate.add(Calendar.DATE, +4);
                                   dp.setMinDate(minDate.getTimeInMillis());
                                   
                                   Calendar maxDate = Calendar.getInstance();
                                   maxDate.add(Calendar.DATE, +35);
                           		   dp.setMaxDate(maxDate.getTimeInMillis());
                                   return mDatePickerDialog;
                       // create a new TimePickerDialog with values you want to show 
                               case TIME_DIALOG_ID:
                                   TimePickerDialog mTimePickerDialog =  new TimePickerDialog(this,
                                           mTimeSetListener, mHour, mMinute, false);
                                   
                                   return mTimePickerDialog;
                               }
                               return null;
                           }
                           
                           @Override
                           public boolean onKeyDown(int keyCode, KeyEvent event) 
                       	{
                               if ((keyCode == KeyEvent.KEYCODE_BACK)) 
                               {
                               	Log.d("DERYL", "KEY BACK PRESSED");
                               	goBackToHomePage();
                                   return true; 
                               }
                               return super.onKeyDown(keyCode, event);
                       	}
                       	
                       	
                       	private void goBackToHomePage()
                       	{
                       		Intent intent = new Intent(CustomerActivity.this, MainActivity.class); 
                       		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       		startActivity(intent);
                       		// NEED TO HANDLE EMPTY OF ORDER LIST AND CART CONDITION 
                       	}
                     
	

}
