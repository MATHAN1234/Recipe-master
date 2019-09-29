package com.example.recipe_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MyRecipeIngredients extends Fragment {

    TextView ingone, ingtwo, ingthree, ingfour;

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ingone = (TextView) getView().findViewById(R.id.ing1i);
        ingtwo = (TextView) getView().findViewById(R.id.ing2i);
        ingthree = (TextView) getView().findViewById(R.id.ing3i);
        ingfour = (TextView) getView().findViewById(R.id.ing4i);

        Bundle mBundle = getActivity().getIntent().getExtras();
        if(mBundle != null){

            ingone.setText(mBundle.getString("inga1"));
            ingtwo.setText(mBundle.getString("ingb1"));
            ingthree.setText(mBundle.getString("ingc1"));
            ingfour.setText(mBundle.getString("ingd1"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_recipe_ingredients, container, false);
    }


}
