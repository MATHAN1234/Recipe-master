package com.example.recipe_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class login extends AppCompatActivity {

    TextView textView;
    private EditText textemail;
    private EditText textpassword;
    FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textemail = (EditText)findViewById(R.id.emailuser);
        textpassword = (EditText)findViewById(R.id.passwordUser);
        textView = findViewById(R.id.goregister);

        mAuth = FirebaseAuth.getInstance();

        //: Auto Signin
        Paper.init(this);

        String user = Paper.book().read(Common.UserField);
        String pasw = Paper.book().read(Common.PasField);

        if(user!= null && pasw!=null)
        {
            if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pasw))
            {
                autoLogin(user,pasw);
            }

        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.loginuser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password= textpassword.getEditableText().toString().trim();
                final String email= textemail.getEditableText().toString().trim();


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog mDialog = new ProgressDialog(login.this);
                mDialog.setMessage("signing in....");
                mDialog.show();
                    mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                        mDialog.dismiss();
                        FirebaseDatabase.getInstance().getReference(Common.recipeuser)
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                // saving remember value
                                Paper.book().write(Common.UserField,textemail.getText().toString());
                                Paper.book().write(Common.PasField,textpassword.getText().toString());

                                Common.currentUser = dataSnapshot.getValue(User.class);
                                startActivity( new Intent(login.this,Home.class));
                                Toast.makeText(login.this, "Sign in Successully", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                mDialog.dismiss();
                            }
                        });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mDialog.dismiss();
                            Toast.makeText(login.this, "Failed to Sign In" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
                        });
    }

    private void autoLogin(String user, String pasw) {

        final ProgressDialog mDialog = new ProgressDialog(login.this);
        mDialog.setMessage("signing in....");
        mDialog.show();

        mAuth.signInWithEmailAndPassword(user,pasw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                mDialog.dismiss();
                FirebaseDatabase.getInstance().getReference(Common.recipeuser).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Common.currentUser= dataSnapshot.getValue(User.class);
                        startActivity( new Intent(login.this,Home.class));
                        finish();
                        Toast.makeText(login.this, "Welcome Back", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login.this, "Failed to Sign In" + e.getMessage(), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });

    }

}
