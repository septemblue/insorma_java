package com.septemblue.insorma.storage;

public class TransactionHistoryModel {
    public int transactionId;
    public String furnitureName;
    public int quantity;
    public String price;
    public String date;

    @Override
    public String toString() {
        return "TransactionHistoryModel{" +
                "transactionId=" + transactionId +
                ", furnitureName='" + furnitureName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
