package com.example.eps;

import android.app.NotificationManager;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//          //  Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
//                handleNow();
//            }
//
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.


        String Title = remoteMessage.getNotification().getTitle();
        String Message = remoteMessage.getNotification().getBody();
        remoteMessage.getNotification().getImageUrl();

        notification(Title, Message);

    }

    public void notification(String title, String Message) {
//        MediaPlayer player = MediaPlayer.create(this, R.raw.notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "My Notification").setContentTitle(title)
                .setSmallIcon(R.mipmap.app_icon)
//                .setSound()
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setVibrate(new long[]{1, 0, 1, 0, 1, 0, 1, 0})
                .setAutoCancel(true).setContentText(Message);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());
//        player.start();
    }

}
