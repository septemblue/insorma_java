package com.septemblue.insorma.storage;

public class ProductModel {
    public int productID;
    public String productName;
    public String productPrice;
    public String productImage;
    public String productDescription;

    public ProductModel(String productName, String productPrice, String productImage, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
