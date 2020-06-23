package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class PurchaseActivity extends AppCompatActivity {
    ImageView PurchaseProductImage;
    TextView PurchaseProductName;
    TextView PurchaseProductPrice;
    Button PurchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        String Link = getIntent().getStringExtra("Name");
        Toast.makeText(PurchaseActivity.this, Link, Toast.LENGTH_LONG).show();

        final int index = Integer.parseInt(Link);

        ProductOverView product = Backend.list.get(index);

        PurchaseProductImage = findViewById(R.id.PurchaseProductImage);
        PurchaseProductImage.setImageBitmap(product.MainBitmap);

        PurchaseProductName = findViewById(R.id.PurchaseProductName);
        PurchaseProductName.setText(product.NameOfProduct);

        PurchaseProductPrice = findViewById(R.id.PurchaseProductPrice);
        PurchaseProductPrice.setText(product.Price);

        PurchaseButton = findViewById(R.id.PurchaseButton);

        PurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backend backend = new Backend();
                backend.purchase(index);
                Snackbar.make(v, "Your Request is send to our Server. Your product will be delivered within 7 days", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}