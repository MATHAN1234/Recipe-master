package com.example.recipe_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyRecipeAbout extends Fragment {
    TextView foodname, foodcategory, username;
    String text;

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodname = (TextView) getView().findViewById(R.id.foodnamei);
        username = (TextView)getView().findViewById(R.id.nametexttti);
        foodcategory = (TextView)getView().findViewById(R.id.categoryi);

        Bundle mBundle = getActivity().getIntent().getExtras();
        if(mBundle != null){
            foodname.setText(mBundle.getString("Name1"));
            username.setText((mBundle.getString("username1")));
            foodcategory.setText(mBundle.getString("Category1"));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_recipe_about, container, false);
    }
}
