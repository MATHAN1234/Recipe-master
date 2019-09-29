package com.example.recipe_app;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.recipe_app.Model.Fooddata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment {

    RecyclerView mRecyclerView;
    List<Fooddata> myFoodList;
    Fooddata mfooddata;
    EditText txt_Search;

    Myadapter myadapter;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView)getView().findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search = (EditText)getView().findViewById(R.id.txt_searchbar);

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

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    //String child = itemSnapshot.getKey();
                    Fooddata fooddata = itemSnapshot.getValue(Fooddata.class);
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

        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    private void filter(String text) {

        ArrayList<Fooddata> filterList = new ArrayList<>();

        for(Fooddata item:  myFoodList){

            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){

                filterList.add(item);
            }
        }
        myadapter.filteredList(filterList);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_search, null);
    }
}
