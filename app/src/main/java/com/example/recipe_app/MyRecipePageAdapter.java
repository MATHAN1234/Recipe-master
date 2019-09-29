package com.example.recipe_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyRecipePageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    MyRecipePageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MyRecipeIngredients();
            case 1:
                return new myrecipedirection();
            case 2:
                return new MyRecipeAbout();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
