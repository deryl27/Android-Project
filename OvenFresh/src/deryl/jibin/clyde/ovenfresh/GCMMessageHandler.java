package deryl.jibin.clyde.ovenfresh;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import deryl.jibin.clyde.ovenfresh.utils.Constants;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;



public class GCMMessageHandler  extends IntentService {

    String mes;
    private Handler handler;
   public GCMMessageHandler() {
       super("GcmMessageHandler");
   }

   @Override
   public void onCreate() {
       // TODO Auto-generated method stub
       super.onCreate();
       handler = new Handler();
   }
   @Override
   protected void onHandleIntent(Intent intent) {
       Bundle extras = intent.getExtras();

       GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
       // The getMessageType() intent parameter must be the intent you received
       // in your BroadcastReceiver.
       String messageType = gcm.getMessageType(intent);

      mes = extras.getString("title");
      showToast();
      Log.i("GCM", "Received : (" +messageType+")  "+extras.getString("title"));

      GCMReceiver.completeWakefulIntent(intent);

   }

   public void showToast(){
       handler.post(new Runnable() {
           public void run() {
              // Toast.makeText(getApplicationContext(),mes , Toast.LENGTH_LONG).show();
               
               showNotification(mes);
               
           }
        });
   }
             
       
public void showNotification(String mes)
{
    	           // define sound URI, the sound to be played when there's a notification
    	           Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    	           // intent triggered, you can add other intent for other actions
    	           Intent intent = new Intent(getApplicationContext(), MYBookingActivity.class);
    	           PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
    	           // this is it, we'll build the notification!
    	          
    	        Constants.isFromNotification = true;
    	        Notification n  = new Notification.Builder(this)
    	                .setContentTitle("Order Status Message")
    	                .setContentText(mes)
    	                .setSmallIcon(R.drawable.iconnewbig)
    	                .setContentIntent(pIntent)
    	                .setSound(soundUri)
    	                .setAutoCancel(true).getNotification();
    	            
    	          
    	        NotificationManager notificationManager = 
    	          (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    	        notificationManager.notify(0, n); 

   }
}

