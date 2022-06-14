package com.septemblue.insorma.main.dataclass;

import java.util.List;

public class Furnitures {
    List<Furniture> furnitures;

    public List<Furniture> getFurnitures() {
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
    public void setFurnitures(List<Furniture> furnitures) {
        this.furnitures = furnitures;
    }


}
