package com.aynna.app.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private static final String URL = "https://moneyless-woodcock-5617.dataplicity.io/";
    private static final String loginRequest = "login";
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email_input = (EditText) findViewById(R.id.email_input);
        final EditText password_input = (EditText) findViewById(R.id.password_input);

        final TextView responseText = (TextView) findViewById(R.id.response);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        loginButton.setReadPermissions("email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Intent homeScreenIntent = new Intent(Login.this, Home.class);
                Profile profile = Profile.getCurrentProfile();
                Log.v("Login", profile.getFirstName());
                Log.v("Login", loginResult.getAccessToken().getToken());
                responseText.setText(loginResult.getAccessToken().getToken() + "\n" + profile.getName() + "\n" + profile.getId());
                homeScreenIntent.putExtra(Home.KEY_ACCESS_TOKEN, loginResult.getAccessToken().getToken());
                homeScreenIntent.putExtra(Home.PROFILE_NAME, profile.getName());
                startActivity(homeScreenIntent);
            }

            @Override
            public void onCancel() {
                // App code
                Log.v("Login", "Canceled Login");
                responseText.setText("Login Canceled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e("Login", exception.getMessage());
                responseText.setText(exception.getMessage());
            }
        });

        this.requestQueue = Volley.newRequestQueue(this);
        this.stringRequest = new StringRequest(Request.Method.POST, URL + loginRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("Log in", response);
                responseText.setText("Your Response\n" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Log in", "Something Went Wrong", error);
                responseText.setText("Something Went Wrong\n" + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email_input.getText().toString());
                params.put("password", md5(password_input.getText().toString()));

                return params;
            }
        };
//        this.requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loginRequest(View view) {
//        Log.v("Log in","inside loginRequest to queue request...");
        this.requestQueue.add(stringRequest);
//        Log.v("Log in","Request Queued...");

    }
}
