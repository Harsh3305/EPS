package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class WaitingScreen extends AppCompatActivity {

    TextView text;
    ProgressBar progressBar;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);
        progressBar = findViewById(R.id.progressBar);



        text = findViewById(R.id.Loading);


        prog();
        if (counter>= 100) {
            startActivity(new Intent(WaitingScreen.this, Home.class));
            finish();
        }



    }


    private void prog() {
        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBar.setProgress(counter);

                if (counter == 100) {
                    timer.cancel();
                    startActivity(new Intent(WaitingScreen.this, Home.class));
                    finish();
                    return;
                }
            }
        };
        timer.schedule(task, 0, 140);
        if (counter >=100) {
            startActivity(new Intent(WaitingScreen.this, Home.class));
            finish();
        }
    }
}