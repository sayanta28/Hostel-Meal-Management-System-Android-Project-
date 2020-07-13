package com.example.hostelapp.testTab;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hostelapp.Details_Activity;
import com.example.hostelapp.testTab.utils.DataPassing;

import java.util.LinkedHashMap;
import java.util.List;

public class MyAdapter extends FragmentPagerAdapter{


    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }



    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BreakFastFragment breakFastFragment = new BreakFastFragment();
                return breakFastFragment;
            case 1:
                LunchFragment sportFragment = new LunchFragment();
                return sportFragment;
            case 2:
                DinnerFragment movieFragment = new DinnerFragment();
                return movieFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}
