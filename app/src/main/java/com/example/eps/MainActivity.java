package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int counter;
    ProgressBar MainScreenProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainScreenProgressBar = findViewById(R.id.MainScreenProgressBar);

        Backend backend = new Backend();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser User = mAuth.getCurrentUser();

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