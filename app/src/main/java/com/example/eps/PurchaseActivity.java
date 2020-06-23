package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        String Link = getIntent().getStringExtra("Name");
        Toast.makeText(PurchaseActivity.this, Link, Toast.LENGTH_LONG).show();

    }
}