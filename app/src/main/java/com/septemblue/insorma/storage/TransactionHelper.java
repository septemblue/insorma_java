package com.septemblue.insorma.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TransactionHelper {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public static ArrayList<TransactionModel> transactions = new ArrayList<>();

    public TransactionHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void addTransaction(TransactionModel transaction, int userID, int productID) {
        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userID", userID);
        contentValues.put("productID", productID);
        contentValues.put("transactionDate", transaction.transactionDate.toString());
        contentValues.put("quantity", transaction.quantity);

        db.insert("TRANSACTION_TABLE", null, contentValues);
        updateTransactionList();
    }

    private void updateTransactionList() {
        String getAllAccountsQuery = "SELECT * FROM TRANSACTION_TABLE";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int transactionID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                int userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"));
                int productID = cursor.getInt(cursor.getColumnIndexOrThrow("productID"));
                String transactionDate = cursor.getString(cursor.getColumnIndexOrThrow("transactionDate"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                TransactionModel transaction = new TransactionModel(transactionID, userID, productID, transactionDate, quantity);
                transactions.add(transaction);

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        cursor.close();
        db.close();
    }
}
