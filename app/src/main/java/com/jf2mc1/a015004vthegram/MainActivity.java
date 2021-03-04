package com.jf2mc1.a015004vthegram;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
    
    private EditText edtSUEmail, edtSUUsername, edtSUPassword;
    private Button btnSUSignUp, btnSULogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Sign Up");
        
        edtSUEmail = findViewById(R.id.edt_su_email);
        edtSUUsername = findViewById(R.id.edt_su_uname);
        edtSUPassword = findViewById(R.id.edt_su_pword);
        btnSUSignUp = findViewById(R.id.btn_su_signup);
        btnSULogin = findViewById(R.id.btn_su_login);
        
        btnSUSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
        
        btnSULogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInUser();
            }
        });
        

        
    }


    private void signUpUser() {

        // create new Parse user
        ParseUser appUser = new ParseUser();
        // appUser.setEmail(edtSUEmail.getText().toString());
        appUser.setUsername(edtSUUsername.getText().toString());
        appUser.setPassword(edtSUPassword.getText().toString());

        appUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText(MainActivity.this,
                            appUser.getUsername() + " is signed up :)",
                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {
                    FancyToast.makeText(MainActivity.this,
                            e.getMessage() + ":(",
                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            }
        });
        
        
    }

    private void logInUser() {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }



}