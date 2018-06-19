package net.ngorham.projecttower;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> FragmentList = new ArrayList<>();
    private final List<String> FragmentTitleList = new ArrayList<>();

    //Constructor
    public ViewPagerAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position){
        return FragmentList.get(position);
    }

    @Override
    public int getCount(){
        return FragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        FragmentList.add(fragment);
        FragmentTitleList.add(title);
    }

    @Override
    public String getPageTitle(int position){
        return FragmentTitleList.get(position);
    }
}

