package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eps.ui.gallery.NotificationModel;
import com.example.eps.ui.gallery.NotigicationAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductDescription extends AppCompatActivity {

    private ImageButton BackButton;
    private TextView productName;
    private RecyclerView ProductImageRecyclerView;
    private Button Purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        final String Link = getIntent().getStringExtra("Name");

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

        productName = findViewById(R.id.ProductName);

        productName.setText(Link);

        /////////////////// categoryRecyclerView

        ProductImageRecyclerView = findViewById(R.id.RecycleViewProductImage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ProductImageRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductImagesSliderModel> ProductImagesSliderModelList = new ArrayList<>();

        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));

        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));

        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));

        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));
        ProductImagesSliderModelList.add(new ProductImagesSliderModel(R.mipmap.ic_launcher));


        ProductImagesSliderAdapter categoryAdapter = new ProductImagesSliderAdapter(ProductImagesSliderModelList);
        ProductImageRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


    }
}