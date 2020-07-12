package com.example.eps.ui.gallery;

import android.graphics.Bitmap;

public class NotificationModel {
    private Bitmap Image;
    private String Name;
    int index;

    public NotificationModel(Bitmap image, String name, int index) {
        Image = image;
        Name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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


}
