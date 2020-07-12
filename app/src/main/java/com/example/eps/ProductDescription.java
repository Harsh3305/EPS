package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eps.ui.gallery.NotificationModel;
import com.example.eps.ui.gallery.NotigicationAdaptor;
import com.example.eps.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductDescription extends AppCompatActivity {

    private ImageButton BackButton;
    private TextView productName;
    private RecyclerView ProductImageRecyclerView;
    private Button Purchase;
    private TextView Price;
    private TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        final String Link = getIntent().getStringExtra("Name");
        int index = Integer.parseInt(Link);
        Description = findViewById(R.id.Description);
        Price = findViewById(R.id.Price);


        Bitmap  bitmap =Backend.list.get(index).getMainBitmap();

        ProductOverView product = Backend.list.get(index);

        ProductImageRecyclerView = findViewById(R.id.RecycleViewProductImage);

        BackButton = findViewById(R.id.imageButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Purchase = findViewById(R.id.Purchase);

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDescription.this, PurchaseActivity.class);
                intent.putExtra("Name", Link);
                startActivity(intent);
            }
        });

        //startActivity(new Intent(ProductDescription.this, LoadingActivity.class));

        productName = findViewById(R.id.ProductName);

        productName.setText(product.NameOfProduct);
        if (! product.getDescription().equals("")) {
            Description.setText(product.Description);
        }
        else {
            Description.setText("Not Available");
        }


        Description.setText(product.Description);
        if (! product.getPrice().equals("")) {
            Price.setText(product.Price);
        }
        else {
            Price.setText("Not Available");
        }

        /////////////////// categoryRecyclerView

        ProductImageRecyclerView = findViewById(R.id.RecycleViewProductImage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ProductImageRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductImagesSliderModel> ProductImagesSliderModelList = new ArrayList<>();

        ProductImagesSliderModelList.add(new ProductImagesSliderModel(bitmap));

        ProductImagesSliderAdapter categoryAdapter = new ProductImagesSliderAdapter(ProductImagesSliderModelList);
        ProductImageRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


    }
}