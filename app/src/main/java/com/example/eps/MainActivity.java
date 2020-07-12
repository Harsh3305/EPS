package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int counter;
    ProgressBar MainScreenProgressBar;
    String Subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainScreenProgressBar = findViewById(R.id.MainScreenProgressBar);

        Backend backend = new Backend();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser User = mAuth.getCurrentUser();

        if (mAuth.getCurrentUser() == null) {
            Subscription = "NewUser";
        }
        else if (! mAuth.getCurrentUser().isEmailVerified()) {
            Subscription = "NotVerify";
        }
        else {
            Subscription = "ExistingUser";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic(Subscription)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


//
//        if (User.isEmailVerified()) {
//            Toast.makeText(this, "Ver", Toast.LENGTH_SHORT).show();
//        }
//
//
//        if (User != null && User. isEmailVerified()) {
//            backend.init();
//            prog();
//        }
//        else if (backend.getUser() != null || ! backend.getUser().isEmailVerified()) {
//            startActivity(new Intent(MainActivity.this, Verification.class));
////            finish();
//        }
//        else {
//            startActivity(new Intent(MainActivity.this,SignUp.class));
//            finish();
//        }
        if (User == null || User.getEmail()  == null) {
            startActivity(new Intent(MainActivity.this,SignUp.class));
            finish();
        }
        else {
            backend.init();
            prog();
        }
    }

    private void prog() {
        final Timer timer = new Timer();

//        final Backend backend = new Backend();
//        final FirebaseUser user = backend.getUser();

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;

                // Obtain the FirebaseAnalytics instance.


                if (counter == 100) {
                    timer.cancel();
                    FirebaseAuth mAuth= FirebaseAuth.getInstance();

                    FirebaseUser user = mAuth.getCurrentUser();

                    if (user == null) {
                        startActivity(new Intent(MainActivity.this, SignUp.class));
                    }
//                    else if (! user.isEmailVerified()) {
//                        startActivity(new Intent(MainActivity.this, Verification.class));
//                    }
                    else {
//                        backend.init();

                        startActivity(new Intent(MainActivity.this, WaitingScreen.class));
                        // TODO: Call Waiting Screen


                    }
                    finish();
                    return;
                }


                MainScreenProgressBar.setProgress(counter);

            }
        };
        timer.schedule(task, 0, 140);
        if (counter >=100) {

            FirebaseAuth mAuth= FirebaseAuth.getInstance();

            FirebaseUser user = mAuth.getCurrentUser();

            if (user == null) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
            else {
//                backend.init();

                startActivity(new Intent(MainActivity.this, WaitingScreen.class));
                // TODO: Call Waiting Screen
            }
            finish();
        }
    }
}