package com.example.recipe_app;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recipe_app.Model.Fooddata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homepage extends  Fragment {

    RecyclerView mRecyclerView;
    List<Fooddata> myFoodList;
    Fooddata mfooddata;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    Spinner customSpinner;
    Myadapter myadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_homepage, null);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customSpinner = (Spinner)getView().findViewById(R.id.fooditemcategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.recipecategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customSpinner.setAdapter(adapter);

        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                if(text.equals("Chicken")){
                    UploadRecipeChicken();
                }else if(text.equals("Veg")){
                    UploadRecipeVeg();
                }else if(text.equals("Beef")){
                    UploadRecipeBeef();
                }else{
                    UploadRecipePork();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void UploadRecipeBeef(){

            mRecyclerView = (RecyclerView)getView().findViewById(R.id.recycleview);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading Items");

            myFoodList = new ArrayList<>();

            myadapter = new Myadapter(getActivity(), myFoodList);
            mRecyclerView.setAdapter(myadapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
            progressDialog.show();

            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    myFoodList.clear();

                    for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                        Fooddata fooddata = itemSnapshot.getValue(Fooddata.class);
                        fooddata.setKey(itemSnapshot.getKey());
                        if(fooddata.getItemCategory().equals("Beef"))
                        myFoodList.add(fooddata);
                    }
                    myadapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });
        }

    public void UploadRecipeChicken(){

        mRecyclerView = (RecyclerView)getView().findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Items");

        myFoodList = new ArrayList<>();

        myadapter = new Myadapter(getActivity(), myFoodList);
        mRecyclerView.setAdapter(myadapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
       // databaseReference = FirebaseDatabase.getInstance().getReference("RecipeChicken");
        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myFoodList.clear();
                    for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                        Fooddata fooddata = itemSnapshot.getValue(Fooddata.class);
                        fooddata.setKey(itemSnapshot.getKey());
                        if(fooddata.getItemCategory().equals("Chicken"))
                        myFoodList.add(fooddata);
                    }
                myadapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
    public void UploadRecipeVeg(){

        mRecyclerView = (RecyclerView)getView().findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Items");

        myFoodList = new ArrayList<>();

        myadapter = new Myadapter(getActivity(), myFoodList);
        mRecyclerView.setAdapter(myadapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myFoodList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    Fooddata fooddata = itemSnapshot.getValue(Fooddata.class);
                    fooddata.setKey(itemSnapshot.getKey());
                    if(fooddata.getItemCategory().equals("Veg"))
                    myFoodList.add(fooddata);
                }
                myadapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
    public void UploadRecipePork(){

        mRecyclerView = (RecyclerView)getView().findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Items");

        myFoodList = new ArrayList<>();

        myadapter = new Myadapter(getActivity(), myFoodList);
        mRecyclerView.setAdapter(myadapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myFoodList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    Fooddata fooddata = itemSnapshot.getValue(Fooddata.class);
                    fooddata.setKey(itemSnapshot.getKey());
                    if(fooddata.getItemCategory().equals("Pork")) {
                        myFoodList.add(fooddata);
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
    }
        }
