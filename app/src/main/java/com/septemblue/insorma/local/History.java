package com.septemblue.insorma.local;

import java.util.Date;

public class History {
    public int transId;
    public int quantity;
    public Furniture furniture;
    public int totalPrice;
    public Date transDate;

    public History(int transId, int quantity, Furniture checkedOutFurniture, int totalPrice, Date transDate) {
        this.transId = transId;
        this.furniture = checkedOutFurniture;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transDate = transDate;
    }

}
