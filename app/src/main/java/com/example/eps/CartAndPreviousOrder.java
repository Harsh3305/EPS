package com.example.eps;

public class CartAndPreviousOrder {
    String NameOfProduct;
    String Description;
    String Index;
    String price;
    String isCart;

    public CartAndPreviousOrder() {
    }

    public String getNameOfProduct() {
        return NameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        NameOfProduct = nameOfProduct;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsCart() {
        return isCart;
    }

    public void setIsCart(String isCart) {
        this.isCart = isCart;
    }
}
