package Utils;

import java.util.List;

import com.example.weatehrapi.CityWeatherDetailFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class CityPageAdapter extends FragmentPagerAdapter {
	
	Fragment fragment = null;
	List<String> city ;
	public CityPageAdapter(FragmentManager fm, List<String> city) 
	{
		super(fm);
		this.city = city;
	}
	

    @Override
    public Fragment getItem(int i) {
                fragment = new CityWeatherDetailFragment();
                Bundle args = new Bundle();
                args.putString(CityWeatherDetailFragment.ARG_SECTION_NUMBER, city.get(i).toString());
                fragment.setArguments(args);
                return fragment;
    }
    
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return city.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return city.get(position);
    }
}