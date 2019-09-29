package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;

public class userprofile extends AppCompatActivity {

    TextView Name,email;
    Button buttonone;
    TextView backtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        Name = (TextView)findViewById(R.id.nametext);
        email   = (TextView)findViewById(R.id.emailtext);

        backtext = (TextView)findViewById(R.id.gobackbutton);

        Name.setText(Common.currentUser.getName());
        email.setText(Common.currentUser.getEmail());

        buttonone = (Button)findViewById(R.id.logout);

        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.init(userprofile.this);
                Paper.book().destroy();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(userprofile.this, login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        backtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userprofile.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
