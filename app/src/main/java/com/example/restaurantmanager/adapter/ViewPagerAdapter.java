package com.example.restaurantmanager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fms;
    public ViewPagerAdapter(@NonNull FragmentManager fm, Fragment...fms) {
        super(fm);
        this.fms = fms;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fms[position];
    }

    @Override
    public int getCount() {
        return fms.length;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fms[position].getTitle();
//    }
}
