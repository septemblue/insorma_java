package com.septemblue.insorma.local;

import java.util.Date;

public class Transaction {
    public int transId;
    public int quantity;
    public Product product;
    public int totalPrice;
    public Date transDate;

    public Transaction(int transId, int quantity, Product checkedOutProduct, int totalPrice, Date transDate) {
        this.transId = transId;
        this.product = checkedOutProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transDate = transDate;
    }

}
