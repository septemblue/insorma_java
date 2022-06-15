package com.septemblue.insorma.main.dataclass;

import java.util.ArrayList;
import java.util.List;

public class Furnitures {
    ArrayList<Furniture> furnitures;

    public ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }


    // to string


    @Override
    public String toString() {
        return "Furnitures{" +
                "furnitures=" + furnitures +
                '}';
    }

    // setter and getter

    public void setFurnitures(ArrayList<Furniture> furnitures) {
        this.furnitures = furnitures;
    }
}
