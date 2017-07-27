package com.aynna.app.testapp;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushMessageService extends FirebaseMessagingService {

    private static final String LOG = PushMessageService.class.getName();

    public PushMessageService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(LOG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size()>0){
            Log.d(LOG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(LOG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }
}
