package com.jf2mc1.a015004vthegram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLIUsername, edtLIPassword;
    private Button btnLILogin, btnLISignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Log In");

        edtLIUsername = findViewById(R.id.edt_li_uname);
        edtLIPassword = findViewById(R.id.edt_li_pword);
        btnLILogin = findViewById(R.id.btn_li_login);
        btnLISignUp = findViewById(R.id.btn_li_signup);

        btnLILogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInUser();
            }
        });

        btnLISignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToSignUp();
            }
        });


    }

    private void backToSignUp() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

    private void logInUser() {

        ParseUser.logInInBackground(edtLIUsername.getText().toString(),
                edtLIPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(LoginActivity.this,
                                    user.getUsername() + " is logged in :)",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        } else {
                            FancyToast.makeText(LoginActivity.this,
                                    e.getMessage() + ": user details not known :(",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
    }
}
