package com.example.nterlien;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmenListTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return FragmenListTitles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return FragmenListTitles.get(position);
    }



    public void AddFragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        FragmenListTitles.add(Title);
    }
}

