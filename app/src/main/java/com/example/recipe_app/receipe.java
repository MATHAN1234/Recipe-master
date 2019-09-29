package com.example.recipe_app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_app.Model.Fooddata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class receipe extends Fragment {

    Spinner categorySpinner;
    Button uploadImagebutton, uploadRecipebutton;
    ImageView recipeIImage;
    EditText txt_name,txt_ingone, txt_ingtwo, txt_ingthree, txt_ingfour, txt_stepone,
            txt_steptwo, txt_stepthree, txt_stepfour;
    TextView txt_username;
    TextView txt_date;
    Uri uri;
    String imageUrl;

    @SuppressLint("WrongViewCast")
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeIImage = (ImageView)getView().findViewById(R.id.iv_foodImage);
        uploadImagebutton = (Button)getView().findViewById(R.id.uploadimage);
        uploadRecipebutton = (Button)getView().findViewById(R.id.uploadRecipe);

        txt_username = (TextView)getView().findViewById(R.id.nametextt);
        txt_username.setText(Common.currentUser.getName());


        txt_date = (TextView)getView().findViewById(R.id.datetime);
        txt_name = (EditText)getView().findViewById(R.id.foodname);
        txt_ingone = (EditText)getView().findViewById(R.id.fooding1);
        txt_ingtwo = (EditText)getView().findViewById(R.id.fooding2);
        txt_ingthree = (EditText)getView().findViewById(R.id.fooding3);
        txt_ingfour = (EditText)getView().findViewById(R.id.fooding4);
        txt_stepone = (EditText)getView().findViewById(R.id.foodstep1);
        txt_steptwo = (EditText)getView().findViewById(R.id.foodstep2);
        txt_stepthree = (EditText)getView().findViewById(R.id.foodstep3);
        txt_stepfour = (EditText)getView().findViewById(R.id.foodstep4);
        categorySpinner = (Spinner)getView().findViewById(R.id.foodcategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.recipecategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        uploadImagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker,1);
            }
        });

        uploadRecipebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null && data.getData() != null ){
            uri = data.getData();
            recipeIImage.setImageURI(uri);
        }else {
            Toast.makeText(getContext(), "You haven't Picked image yet", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImage(){

        if(uri != null){
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("RecipeImage").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Recipe Uploading....");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();

                //String text = categorySpinner.getSelectedItem().toString();
                uploadRecipe();
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            progressDialog.dismiss();
            }
        });
    }else{
            Toast.makeText(getContext(),"Upload Image from Your gallery first!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void uploadRecipe(){

        Fooddata fooddata = new Fooddata(

                txt_username.getText().toString(),
                imageUrl,
                txt_name.getText().toString(),
                categorySpinner.getSelectedItem().toString(),
                txt_ingone.getText().toString(),
                txt_ingtwo.getText().toString(),
                txt_ingthree.getText().toString(),
                txt_ingfour.getText().toString(),
                txt_stepone.getText().toString(),
                txt_steptwo.getText().toString(),
                txt_stepthree.getText().toString(),
                txt_stepfour.getText().toString()

        );
        String myCurrentDataTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(myCurrentDataTime).setValue(fooddata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(),"Recipe Uploaded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), Home.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void validation(){

        String Foodname = txt_name.getEditableText().toString();
        String Foodingone = txt_ingone.getEditableText().toString();
        String Foodingtwo = txt_ingtwo.getEditableText().toString();
        String Foodingthree = txt_ingthree.getEditableText().toString();
        String Foodingfour = txt_ingfour.getEditableText().toString();
        String Foodstepone = txt_stepone.getEditableText().toString();
        String Foodsteptwo = txt_steptwo.getEditableText().toString();
        String Foodstepthree = txt_stepthree.getEditableText().toString();
        String Foodstepfour = txt_stepfour.getEditableText().toString();

        if(TextUtils.isEmpty(Foodname))
        {
            Toast.makeText(getContext(), "Enter Recipe Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodingone))
        {
            Toast.makeText(getContext(), "Your RecipeIng1 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodingtwo))
        {
            Toast.makeText(getContext(), "Your RecipeIng2 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodingthree))
        {
            Toast.makeText(getContext(), "Your RecipeIng3 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodingfour))
        {
            Toast.makeText(getContext(), "Your RecipeIng4 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodstepone))
        {
            Toast.makeText(getContext(), "Your RecipeStep1 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodsteptwo))
        {
            Toast.makeText(getContext(), "Your RecipeStep2 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodstepthree))
        {
            Toast.makeText(getContext(), "Your RecipeStep3 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Foodstepfour))
        {
            Toast.makeText(getContext(), "Your RecipeStep4 Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            uploadImage();
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_receipe, null);
    }
}
