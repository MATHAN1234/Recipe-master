package com.example.recipe_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.recipe_app.Model.Fooddata;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 */

public class Ingredients extends Fragment {

    TextView ingone, ingtwo, ingthree, ingfour;

    String key="";
    String imageUrl="";
    Button button;

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

                  ingone = (TextView) getView().findViewById(R.id.ing1);
                  ingtwo = (TextView) getView().findViewById(R.id.ing2);
                  ingthree = (TextView) getView().findViewById(R.id.ing3);
                  ingfour = (TextView) getView().findViewById(R.id.ing4);

                  Bundle mBundle = getActivity().getIntent().getExtras();
                    if(mBundle != null){

                        ingone.setText(mBundle.getString("inga"));
                        ingtwo.setText(mBundle.getString("ingb"));
                        ingthree.setText(mBundle.getString("ingc"));

                        key = mBundle.getString("keyValue");
                        imageUrl = mBundle.getString("Image");
                        ingfour.setText(mBundle.getString("ingd"));
                    }
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }
}
