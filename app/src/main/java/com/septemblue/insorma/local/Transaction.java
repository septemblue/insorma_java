package com.septemblue.insorma.local;

import com.septemblue.insorma.main.dataclass.Furniture;
import com.septemblue.insorma.storage.UserModel;

import java.util.Date;

public class Transaction {
    public int transId;
    public int quantity;
    public Furniture checkedFurniture;
    public UserModel user;
    public int totalPrice;
    public Date transDate;

    public Transaction(int transId, UserModel user, int quantity, Furniture checkedFurniture, int totalPrice, Date transDate) {
        this.transId = transId;
        this.user = user;
        this.checkedFurniture = checkedFurniture;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transDate = transDate;
    }

}
