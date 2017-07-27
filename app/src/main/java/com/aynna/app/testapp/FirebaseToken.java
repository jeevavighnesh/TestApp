package com.aynna.app.testapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by vikki on 27/7/17.
 */

public class FirebaseToken extends FirebaseInstanceIdService {

    private static final String LOG = FirebaseToken.class.getName();

    private String refreshedToken;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG, "Refreshed Token" + refreshedToken);

    }
}
