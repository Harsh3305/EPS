package com.example.eps;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ProductOverView {

    String NameOfProduct;
    String Price = "";
    String Description = "";
    Bitmap MainBitmap;
    int index = -1;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    ArrayList<Bitmap> Bitmaps;

    public ArrayList<Bitmap> getBitmaps() {
        return Bitmaps;
    }

    public void setBitmaps(ArrayList<Bitmap> bitmaps) {
        Bitmaps = bitmaps;
    }

    public ProductOverView(String nameOfProduct, String price, String description) {
        NameOfProduct = nameOfProduct;
        Price = price;
        Description = description;
    }

    public Bitmap getMainBitmap() {
        return MainBitmap;
    }

    public void setMainBitmap(Bitmap mainBitmap) {
        MainBitmap = mainBitmap;
    }

    public void addBitmap(Bitmap bitmap) {
        Bitmaps.add(bitmap);
    }

    public String getNameOfProduct() {
        return NameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        NameOfProduct = nameOfProduct;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
