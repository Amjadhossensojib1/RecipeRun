package com.dalazu.reciperun.fcm;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessegeReceiver extends FirebaseMessegingService {


    public FirebaseMessegeReceiver() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
    }
}
