package com.jf2mc1.a015004vthegram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements
View.OnClickListener {

    private EditText edtLIEmail, edtLIPassword;
    private Button btnLILogin, btnLISignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Log In");
        btnLILogin = findViewById(R.id.btn_li_login);
        btnLISignUp = findViewById(R.id.btn_li_signup);

        edtLIEmail = findViewById(R.id.edt_li_email);
        edtLIPassword = findViewById(R.id.edt_li_pword);
        // keyword enter == login
        edtLIPassword.setOnKeyListener(new View.OnKeyListener() {
            //            What this means is that you've now told the edittext view that you want to be
//            informed every time the user presses a key while this EditText has the focus.
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // sign up user
                    onClick(btnLILogin);
                }
                return false;
            }
        });


        btnLILogin.setOnClickListener(this::onClick);

        btnLISignUp.setOnClickListener(this::onClick);

        // avoid problems with token session
        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }


    }

    private void backToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent);

    }

    private void logInUser() {

        if (edtLIEmail.getText().toString().equals("") ||
                edtLIPassword.getText().toString().equals("")) {

            FancyToast.makeText(LoginActivity.this,
                    "Email & password is required :(",
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        } else {
            Log.i("LOGINX", "in logInUser, else1");
            ParseUser.logInInBackground(edtLIEmail.getText().toString(),
                    edtLIPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                Log.i("LOGINX", "in logInUser, else2");
                                FancyToast.makeText(LoginActivity.this,
                                        user.getUsername() + " is logged in :)",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionToSocialDediaActivity();

                            } else {
                                FancyToast.makeText(LoginActivity.this,
                                        e.getMessage() + ":(",
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_li_login:
                logInUser();
                break;

            case R.id.btn_li_signup:
                backToSignUp();
                break;

            default:
                break;
        }



    }

    // dismiss keyboard
    public void loginRootLayoutTapped(View view) {
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
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }


}
