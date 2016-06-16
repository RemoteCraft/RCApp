package jzl.remotecraft.sensor.ui.adapter;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import jzl.remotecraft.sensor.ui.fragment.BaseFragment;


public class PersonalPagerAdapter extends FragmentStatePagerAdapter {

    public static final int PAGE_COUNT = 2;



    public static final String PERSONAL_TAG = "personal_tag";

    private FloatingActionButton personal_fab;

    private BaseFragment[]childFragments = null;

    public PersonalPagerAdapter(FragmentManager fm ,FloatingActionButton fab,BaseFragment[] cfs) {
        super(fm);
        this.personal_fab = fab;
        childFragments = cfs;
    }

    @Override
    public BaseFragment getItem(int i) {
        Bundle bundle = new Bundle();
        BaseFragment newFragment = childFragments[i];
        newFragment.setFab(personal_fab);
        bundle.putString(PERSONAL_TAG, newFragment.TITLE);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return childFragments[position].TITLE;
    }


    public BaseFragment getFB(int position) {
        if (getItem(position) != null) {
            return getItem(position);
        } else {
            return null;
        }
    }

}
