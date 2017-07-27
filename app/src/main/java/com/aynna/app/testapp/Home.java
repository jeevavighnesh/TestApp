package com.aynna.app.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Home extends AppCompatActivity {

    private static final String LOG = Home.class.getName();
    public static final String KEY_ACCESS_TOKEN = "Access_Token";
    public static final String PROFILE_NAME = "Profile_Name";

    private String accessToken;
    private String profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        accessToken = getIntent().getStringExtra(KEY_ACCESS_TOKEN);
        profileName = getIntent().getStringExtra(PROFILE_NAME);
        Log.v(LOG, "Access Token: " + accessToken + "\n" + "Profile Name: " + profileName);
    }


}
