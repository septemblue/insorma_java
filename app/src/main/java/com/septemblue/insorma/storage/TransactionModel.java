package com.septemblue.insorma.storage;

import java.util.Date;


public class TransactionModel {
    public int transactionID;
    public int userID;
    public int productID;
    public String transactionDate;
    public int quantity;

    public TransactionModel(int userID, int productID, String transactionDate, int quantity) {
        this.userID = userID;
        this.productID = productID;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "transactionID=" + transactionID +
                ", userID=" + userID +
                ", productID=" + productID +
                ", transactionDate='" + transactionDate + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
