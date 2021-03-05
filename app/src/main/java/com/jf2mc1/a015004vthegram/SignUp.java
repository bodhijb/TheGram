package com.jf2mc1.a015004vthegram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtSUEmail, edtSUUsername, edtSUPassword;
    private Button btnSUSignUp, btnSULogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Sign Up");

        edtSUEmail = findViewById(R.id.edt_su_email);
        edtSUUsername = findViewById(R.id.edt_su_uname);
        edtSUPassword = findViewById(R.id.edt_su_pword);
        btnSUSignUp = findViewById(R.id.btn_su_signup);
        btnSULogin = findViewById(R.id.btn_su_login);

        btnSUSignUp.setOnClickListener(this::onClick);
        btnSULogin.setOnClickListener(this::onClick);

        // keyword enter == login
        edtSUPassword.setOnKeyListener(new View.OnKeyListener() {
            //            What this means is that you've now told the edittext view that you want to be
//            informed every time the user presses a key while this EditText has the focus.
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // sign up user
                    onClick(btnSUSignUp);
                }
                return false;
            }
        });

        // avoid problems with token session
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
            transitionToSocialDediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_su_signup:
                signUpUser();
                break;

            case R.id.btn_su_login:
                logInUser();
                break;

            default:
                break;

        }

    }


    private void signUpUser() {

        if (edtSUEmail.getText().toString().equals("") ||
                edtSUUsername.getText().toString().equals("") ||
                edtSUPassword.getText().toString().equals("")) {

            FancyToast.makeText(SignUp.this,
                    "Email, username & password is required :(",
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        } else {
            // create new Parse user
            ParseUser appUser = new ParseUser();
            appUser.setEmail(edtSUEmail.getText().toString());
            appUser.setUsername(edtSUUsername.getText().toString());
            appUser.setPassword(edtSUPassword.getText().toString());

            // show progress dialog
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing up " + edtSUUsername.getText().toString());
            progressDialog.show();

            appUser.signUpInBackground(new SignUpCallback() {
                                           @Override
                                           public void done(ParseException e) {
                                               if (e == null) {
                                                   FancyToast.makeText(SignUp.this,
                                                           appUser.getUsername() + " is signed up :)",
                                                           FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                                   transitionToSocialDediaActivity();

                                               } else {
                                                   FancyToast.makeText(SignUp.this,
                                                           e.getMessage() + ":(",
                                                           FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                               }
                                               progressDialog.dismiss();
                                           }
                                       }
            );
        }

    }

    private void logInUser() {

        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        startActivity(intent);

    }


    // dismiss keyboard
    public void signUpRootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    0
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void transitionToSocialDediaActivity() {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }










}