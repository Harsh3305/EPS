package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.nio.file.Files;
import java.util.Date;

public class PurchaseActivity extends AppCompatActivity {

    ImageView PurchaseProductImage;
    TextView PurchaseProductName;
    TextView PurchaseProductPrice;
    Button PurchaseButton;
    ImageButton Increase;
    ImageButton Decrease;
    int ProductCount = 1;
    TextView ProductNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        String Link = getIntent().getStringExtra("Name");
        //Toast.makeText(PurchaseActivity.this, Link, Toast.LENGTH_LONG).show();

        final int index = Integer.parseInt(Link);
        Increase = findViewById(R.id.Increase);
        Decrease = findViewById(R.id.Decrease);
        ProductNumber = findViewById(R.id.ProductNumber);
        ProductOverView product = Backend.list.get(index);

        PurchaseProductImage = findViewById(R.id.PurchaseProductImage);
        PurchaseProductImage.setImageBitmap(product.MainBitmap);

        PurchaseProductName = findViewById(R.id.PurchaseProductName);
        PurchaseProductName.setText(product.NameOfProduct);

        PurchaseProductPrice = findViewById(R.id.PurchaseProductPrice);
        PurchaseProductPrice.setText(product.Price);

        PurchaseButton = findViewById(R.id.PurchaseButton);

        if (product.Price.equals("") || product.Price.equals("Not Available")) {

            PurchaseButton.setBackgroundColor(getResources().getColor(R.color.Grey));
            PurchaseButton.setEnabled(false);
            Toast.makeText(this, "This product will be available with in few days." +
                    " Sorry for your inconvenience :)", Toast.LENGTH_LONG).show();
        }


        Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Increase();
            }
        });

        Decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Decrease();
            }
        });

        PurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backend backend = new Backend();
                backend.purchase(index);

                if (Settings.Address.equals("")) {
                    Toast.makeText(v.getContext(), "Please enter your Information", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(v.getContext(), Settings.class));
                }
                else {
                    Date date = new Date();
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Snackbar.make(v, "Your Request is send to our Server. Your product will be delivered within 7 days", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    JavaMailAPI mailAPI = new JavaMailAPI(PurchaseActivity.this,backend.mAuth.getCurrentUser().getEmail(), "Thanks For your purchasing " + PurchaseProductName.getText().toString(), "Your Purchase is "  + PurchaseProductName.getText().toString() + "; Count = " + ProductCount + ". For cancellation of product or to five any feedback, " +
                            "please contact us. Our email ID is harshverma3305@gmail.com or call us at 9654499559. You can cancel your product with in 5 days of purchase");
                    mailAPI.execute();

                    JavaMailAPI mailAPI1 = new JavaMailAPI(PurchaseActivity.this, "harshverma3305@gmail.com", "Purchase of " + PurchaseProductName.getText().toString() + "; Count = " + ProductCount, " User Email = " +user.getEmail() + " User UID = "+
                            user.getUid() + " Product Purchase = " + PurchaseProductName.getText().toString() + " Dated on "+ date.getDate() + "/" +
                            (date.getMonth() + 1)  + " On day " + date.getDay() + " Product Id = " + index + "Product Count = " + ProductCount+  " Address = " + Settings.Address);
                    // TODO: add address
                    mailAPI1.execute();
                }
            }
        });
    }
    public void Increase() {
        if (ProductCount < 10) {
            ProductCount++;
            if (ProductCount <=9) {
                ProductNumber.setText("0" + ProductCount);
            }

        }
    }

    public void Decrease() {
        if (ProductCount >1 ) {
            ProductCount--;
            if (ProductCount <=9) {
                ProductNumber.setText("0" + ProductCount);
            }
        }
    }
}