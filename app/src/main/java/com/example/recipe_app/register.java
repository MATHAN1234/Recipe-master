package com.example.recipe_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_app.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

    TextView textView;
    private EditText textemail;
    private EditText textpassword;
    private EditText textname;
    ProgressBar progressbar;
    FirebaseAuth mAuth;
    private FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textView = findViewById(R.id.gologin);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });

        progressbar = new ProgressBar(this);
        textemail = (EditText)findViewById(R.id.reg_email);
        textpassword = (EditText)findViewById(R.id.reg_password_text);
        textname = (EditText)findViewById(R.id.username);
         mAuth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        users=db.getReference(Common.recipeuser);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String password= textpassword.getEditableText().toString().trim();
                 final String email= textemail.getEditableText().toString().trim();
                 final String name= textname.getEditableText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(register.this, "Your Password is too Short.", Toast.LENGTH_SHORT).show();
                }


                final ProgressDialog progressDialog = new ProgressDialog(register.this);
                progressDialog.setMessage("Loading....");
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email,password.toString().trim())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User userAdd = new User();
                        userAdd.setName(name);
                        userAdd.setEmail(email);
                        users.child(mAuth.getInstance().getCurrentUser().getUid()).setValue(userAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                            Toast.makeText(register.this, "Sign up successfull",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(register.this, "Not Successful"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(register.this, "Not Successful"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
