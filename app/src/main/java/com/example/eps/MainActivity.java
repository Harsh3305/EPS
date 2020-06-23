package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;

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

//        SystemClock.sleep(1000);
        Backend backend = new Backend();

        if (backend.getUser() != null) {
            backend.init();
            prog();
        }
        else {
            startActivity(new Intent(MainActivity.this,SignUp.class));
            finish();
        }




    }


    private void prog() {
        final Timer timer = new Timer();

        final Backend backend = new Backend();
        final FirebaseUser user = backend.getUser();

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;

                if (counter == 100) {
                    timer.cancel();


                    if (user == null) {
                        startActivity(new Intent(MainActivity.this, SignUp.class));
                    }
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


            if (user == null) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
            else {
//                backend.init();

                startActivity(new Intent(MainActivity.this, WaitingScreen.class));
                // TODO: Call Waiting Screen


            }
            finish();
            return;
        }
    }
}