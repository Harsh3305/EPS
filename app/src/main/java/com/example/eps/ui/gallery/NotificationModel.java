package com.example.eps.ui.gallery;

import android.graphics.Bitmap;

public class NotificationModel {
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

    public NotificationModel(Bitmap image, String name) {
        Image = image;
        Name = name;
    }
}
