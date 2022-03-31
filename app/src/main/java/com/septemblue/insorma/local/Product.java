package com.septemblue.insorma.local;

import java.util.ArrayList;

public class Product {
    public String id;
    public int imageSource;
    public int rating;
    public String description;
    public String title;
    public int price;

    public Product(String id, int imageSource, String title, int price, int rating, String description) {
        this.id = id;
        this.imageSource = imageSource;
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public static Product getFurniture(ArrayList<Product> products, String id) {
        Product product = null;
        for (Product x :
                products) {
            if (x.id.equals(id)) {
                product = x;
                break;
            }
        }
        return product;
    }

    public static Boolean any(ArrayList<Product> products, String id) {
        boolean exist = false;
        for (Product x :
                products) {
            if (x.id.equals(id)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
