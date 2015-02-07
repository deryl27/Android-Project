package adapter;

import java.util.List;

import com.example.weatehrapi.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
	
	private Activity mActivity;
	private List<String> cities;
	private LayoutInflater mLayoutInflater;
	
	
	 public LazyAdapter(Activity mActivity, List<String> cityName) {
	        this.mActivity = mActivity;
	        this.cities = cityName;		
	        mLayoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cities.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		 	View vi=convertView;
	        if(convertView==null)
	            vi = mLayoutInflater.inflate(R.layout.list_row, null);
	 
	        TextView cityName = (TextView)vi.findViewById(R.id.cityname); 
	        cityName.setText(cities.get(position));
	    	return vi;
	}
	
	

}
