package com.example.recipe_app;

import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailActivity extends AppCompatActivity {

    TextView foodname, foodcategory;
    ImageView foodimage;
    ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backimage = (ImageView)findViewById(R.id.backbutton);
        foodimage = (ImageView)findViewById(R.id.ivImage);
        foodname = (TextView) findViewById(R.id.fname);
        foodcategory = (TextView)findViewById(R.id.cat);


//:: NEW WORK


        TabLayout tabLayout;
        final ViewPager viewPager;
        PageAdapter pageAdapter;
        TabItem tabIng, tabdirection, tababout;

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        tabIng = findViewById(R.id.ingre);
        tabdirection = findViewById(R.id.direction);
        tababout = findViewById(R.id.about);

        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                  }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailActivity.this, Home.class);
                startActivity(intent);
            }
        });

        Bundle mBundle = getIntent().getExtras();
        if(mBundle != null){
            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodimage);
            foodname.setText(mBundle.getString("Name"));
            foodcategory.setText(mBundle.getString("Category"));
        }
    }
}
