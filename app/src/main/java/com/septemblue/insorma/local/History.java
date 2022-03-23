package com.septemblue.insorma.local;

import java.util.Date;

public class History {
    public static int transId = 0;
    public int quantity;
    public Furniture furniture;
    public int totalPrice;
    public Date transDate;

    public History(int quantity, Furniture checkedOutFurniture, int totalPrice, Date transDate) {
        this.furniture = checkedOutFurniture;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transDate = transDate;
    }

}
