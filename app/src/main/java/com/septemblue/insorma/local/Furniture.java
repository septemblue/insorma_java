package com.septemblue.insorma.local;

import java.util.ArrayList;

public class Furniture {
    String id;
    int imageSource;
    String title;
    int price;

    public Furniture(String id, int imageSource, String title, int price) {
        this.id = id;
        this.imageSource = imageSource;
        this.title = title;
        this.price = price;
    }

    public static Furniture getFurniture(ArrayList<Furniture> furnitures, String id) {
        Furniture furniture = null;
        for (Furniture x :
                furnitures) {
            if (x.id.equals(id)) {
                furniture = x;
                break;
            }
        }
        return furniture;
    }

    public static Boolean any(ArrayList<Furniture> furnitures, String id) {
        boolean exist = false;
        for (Furniture x :
                furnitures) {
            if (x.id.equals(id)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
