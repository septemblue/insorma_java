package com.septemblue.insorma.storage;

import java.util.Date;


public class TransactionModel {
    public int transactionID;
    public int userID;
    public int productID;
    public Date transactionDate;
    public int quantity;

    public TransactionModel(int transactionID, int userID, int productID, String transactionDate, int quantity) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.productID = productID;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }
}
