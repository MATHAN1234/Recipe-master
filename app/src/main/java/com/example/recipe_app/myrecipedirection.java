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



public class myrecipedirection extends Fragment {

    TextView stepone, steptwo, stepthree, stepfour;


    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stepone = (TextView)getView().findViewById(R.id.step1i);
        steptwo = (TextView)getView().findViewById(R.id.step2i);
        stepthree = (TextView)getView().findViewById(R.id.step3i);
        stepfour = (TextView)getView().findViewById(R.id.step4i);

        Bundle mBundle = getActivity().getIntent().getExtras();
        if(mBundle != null){

            stepone.setText(mBundle.getString("stepa1"));
            steptwo.setText(mBundle.getString("stepb1"));
            stepthree.setText(mBundle.getString("stepc1"));
            stepfour.setText(mBundle.getString("stepd1"));
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myrecipedirection, container, false);
    }

}
