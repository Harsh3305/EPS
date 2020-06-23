package com.example.eps;

import android.graphics.Bitmap;

public class CategoryModel {
    private Bitmap Image;
    private String Name;

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

    public CategoryModel(Bitmap image, String name) {
        Image = image;
        Name = name;
    }
}
