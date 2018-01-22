package com.pwc.newfind.finding;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by lhuang126 on 1/19/2018.
 */

public class FindingAdapter extends FragmentPagerAdapter {
    public FindingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TestFragment();
            case 1:
                return new TestFragment();
            case 2:
                return new TestFragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
