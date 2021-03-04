package com.jf2mc1.a015004vthegram;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.APP_ID))
                // if defined
                .clientKey(getResources().getString(R.string.CLIENT_KEY))
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
