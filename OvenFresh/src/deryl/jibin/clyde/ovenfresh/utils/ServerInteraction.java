package deryl.jibin.clyde.ovenfresh.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class ServerInteraction {
	
	 public static String getJson(String URL)
	 {	 
		 Log.d("DERYL", "URL : " + URL);
		 String line = null;
		 int timeoutConnection = 13000;
		 HttpParams httpParameters = new BasicHttpParams();
		 HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		 HttpClient oHttpClient =new DefaultHttpClient(httpParameters);
		 HttpGet oHttpGet = new HttpGet(URL);
		 HttpResponse mHttpResponse;
		 try
		 {
			 mHttpResponse = oHttpClient.execute(oHttpGet);
			 Log.i("DERYL", ">>>>>>>>>>>>>"+ mHttpResponse.getStatusLine().toString());
			 HttpEntity mHttpEntity = mHttpResponse.getEntity();
			 InputStream instream = mHttpEntity.getContent();
			 line = convertStreamToString(instream);
			 instream.close();
			 
		 }
		 catch (Exception e) 
		 {
			Log.i("DERYL", "*************ERROR IN SERVER HANDLING **************"+ e.getMessage() + e.getLocalizedMessage()); 
			e.printStackTrace(); 
    	 }
		 
		 return line;
	}
	 
	 private static String convertStreamToString(InputStream is)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb =new StringBuilder();
			
			String result = null;
			try
			{
				while((result = reader.readLine()) != null)
				{
					sb.append(result + "\n");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(is != null)
						is.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			return sb.toString();
		}

}
