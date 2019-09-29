package com.example.recipe_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.recipe_app.Model.Fooddata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myrecipe extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Fooddata> myFoodList;
    Fooddata mfooddata;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    Spinner customSpinner;
    MyRecipeAdapter myadapter;
    TextView textView;
    RelativeLayout relativeLayout;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecipe);

        final String string = Common.currentUser.getName();
        relativeLayout = (RelativeLayout)findViewById(R.id.rellayout);
        textView = (TextView)findViewById(R.id.gohomepage);
         backbutton = (ImageView)findViewById(R.id.backbuttonp);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(myrecipe.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);


        progressDialog = new ProgressDialog(myrecipe.this);
        progressDialog.setMessage("Loading Items");

        myFoodList = new ArrayList<>();

        myadapter = new MyRecipeAdapter(myrecipe.this, myFoodList);
        mRecyclerView.setAdapter(myadapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myFoodList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    Fooddata fooddata = itemSnapshot.getValue(Fooddata.class);
                    fooddata.setKey(itemSnapshot.getKey());
                    if(fooddata.getUsername().equals(string)){
                            myFoodList.add(fooddata);
                            relativeLayout.setVisibility(View.INVISIBLE);
                        } else{

                        }

                }
                myadapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myrecipe.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myrecipe.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
