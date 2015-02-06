package deryl.jibin.clyde.ovenfresh.adapter;

import java.util.List;

import deryl.jibin.clyde.ovenfresh.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<String> 
{
	private Activity mActivity;
	private List<String> spinnerValues;
	
	public SpinnerAdapter(Context context, int textViewResourceId, List<String> objects, Activity mActivity) {
		super(context, textViewResourceId, objects);
		
		this.mActivity = mActivity;
		this.spinnerValues = objects;
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
		return getCustomView(position, cnvtView, prnt);
	}
	@Override
	public View getView(int pos, View cnvtView, ViewGroup prnt) {
		return getCustomView(pos, cnvtView, prnt);
	}
	public View getCustomView(int position, View convertView,
			ViewGroup parent) {
		LayoutInflater inflater = mActivity.getLayoutInflater();
		View mySpinner = inflater.inflate(R.layout.custom_spinner, parent,
				false);
		TextView main_text = (TextView) mySpinner
				.findViewById(R.id.text_custom);
		main_text.setText(spinnerValues.get(position).toString());


		return mySpinner;
	}

}
