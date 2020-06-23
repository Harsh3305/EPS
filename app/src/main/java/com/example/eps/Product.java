package com.example.eps;

public class Product {

    public Product() {
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

    public String getNumberOfImage() {
        return NumberOfImage;
    }

    public void setNumberOfImage(String numberOfImage) {
        NumberOfImage = numberOfImage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    String NameOfProduct;
    String Price = "";
    String NumberOfImage = "";
    String Description = "";

}
