package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView mTextMessage;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Home.this, 1);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new homepage());

    }
    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        switch (item.getItemId()){

            case R.id.navigation_home:
                fragment = new homepage();
                break;

            case R.id.navigation_profile:
                fragment = new profile();
                break;

            case R.id.navigation_receipe:
                fragment = new receipe();
                break;

            case R.id.navigation_search:
                fragment = new Search();
                break;
        }
        return loadFragment(fragment);
    }
}