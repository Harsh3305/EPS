package com.example.eps;

import android.graphics.Bitmap;

public class CategoryModel {
    private Bitmap Image;
    private String Name;
    private String Price;

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public CategoryModel(Bitmap image, String name, String price) {
        Image = image;
        Name = name;
        if (price == null) {
            price = "Not Available";
        }
        Price = price;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
