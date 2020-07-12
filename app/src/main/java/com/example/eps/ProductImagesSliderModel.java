package com.example.eps;

import android.graphics.Bitmap;

public class ProductImagesSliderModel {

    private Bitmap banner;
    private String BackgroundColor;

    public ProductImagesSliderModel(Bitmap banner) {
        this.banner = banner;
        BackgroundColor = "#ffffff";
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public Bitmap getBanner() {
        return banner;
    }

    public void setBanner(Bitmap banner) {
        this.banner = banner;
    }

    public void setBackgroundColor(String backgroundColor) {
        BackgroundColor = backgroundColor;
    }
}
