package com.example.eps;

public class ProductImagesSliderModel {

    private int banner;
    private String BackgroundColor;

    public ProductImagesSliderModel(int banner) {
        this.banner = banner;
        BackgroundColor = "#ffffff";
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public void setBackgroundColor(String backgroundColor) {
        BackgroundColor = backgroundColor;
    }
}
