package com.example.recipe_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {


    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Ingredients();
            case 1:
                return new directions();
            case 2:
                return new aboutrecipe();
            case 3:
                return new profile();
            default:
                return null;

        }
    }
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
