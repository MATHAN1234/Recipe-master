package com.example.recipe_app;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyRecipeDetailActivity extends AppCompatActivity {

    TextView foodname, foodcategory;
    TextView ingone;
    TextView ingtwo, ingthree, ingfour, stepone, steptwo, stepthree, stepfour, username;
    ImageView foodimage;
    ImageView backimage;

    String key="";
    String imageUrl="";
    Button deletebutton;
    Button updatebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterecipe);

        backimage = (ImageView)findViewById(R.id.backbuttoni);
        foodimage = (ImageView)findViewById(R.id.ivImagei);
        foodname = (TextView) findViewById(R.id.fnamei);
        foodcategory = (TextView)findViewById(R.id.cati);
        deletebutton = (Button)findViewById(R.id.deletebutton);

        TabLayout tabLayout;
        final ViewPager viewPager;
        MyRecipePageAdapter myRecipePageAdapter;
        TabItem tabIng, tabdirection, tababout;

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        tabIng = findViewById(R.id.ingre);
        tabdirection = findViewById(R.id.direction);
        tababout = findViewById(R.id.about);

        myRecipePageAdapter = new MyRecipePageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(myRecipePageAdapter);

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

                Intent intent = new Intent(MyRecipeDetailActivity.this, myrecipe.class);
                startActivity(intent);

            }
        });

        Bundle mBundle = getIntent().getExtras();

        if(mBundle != null) {
            Glide.with(this)
                    .load(mBundle.getString("Image1"))
                    .into(foodimage);

            foodname.setText(mBundle.getString("Name1"));
            foodcategory.setText(mBundle.getString("Category1"));

            key = mBundle.getString("keyValue1");
            imageUrl = mBundle.getString("Image1");


        }

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipe");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        reference.child(key).removeValue();
                        Toast.makeText(MyRecipeDetailActivity.this, "Recipe Deleted!! ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MyRecipeDetailActivity.this, myrecipe.class));
                        finish();




                    }
                });

            }
        });



        }
}
