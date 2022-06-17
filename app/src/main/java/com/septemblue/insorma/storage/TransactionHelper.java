package com.septemblue.insorma.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.septemblue.insorma.local.Cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionHelper {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public static ArrayList<TransactionModel> transactions = new ArrayList<>();

    public TransactionHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void addTransaction(TransactionModel transaction) {
        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userID", transaction.userID);
        contentValues.put("productID", transaction.productID);
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

                TransactionModel transaction = new TransactionModel(userID, productID, transactionDate, quantity);
                transaction.transactionID = transactionID;
                transactions.add(transaction);

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        cursor.close();
        db.close();
    }

    public List<TransactionHistoryModel> getUserTransHistory(UserHelper userHelper) {
        userHelper.updateUserList();
        String getAllAccountsQuery = String.format("SELECT t.ID, p.productName, t.quantity, t.transactionDate, p.productprice  FROM TRANSACTION_TABLE t JOIN USER_TABLE u ON t.userID = u.ID JOIN PRODUCT_TABLE p ON p.ID = t.productID WHERE u.ID = %d", Objects.requireNonNull(Cache.getLoggedUser().getValue()).userID);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<TransactionHistoryModel> transHistories = new ArrayList<>();

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TransactionHistoryModel transactionHistory = new TransactionHistoryModel();

                transactionHistory.transactionId = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                transactionHistory.furnitureName = cursor.getString(cursor.getColumnIndexOrThrow("productName"));
                transactionHistory.date = cursor.getString(cursor.getColumnIndexOrThrow("transactionDate"));
                transactionHistory.quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                transactionHistory.price = cursor.getString(cursor.getColumnIndexOrThrow("productPrice"));

                transHistories.add(transactionHistory);
                Log.i("transhisor", transactionHistory.toString());

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        return transHistories;
    }

    public int getUserTransHistoryCount(UserHelper userHelper) {
        userHelper.updateUserList();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String getAllAccountsQuery = String.format("SELECT t.ID, p.productName, t.quantity, t.transactionDate  FROM TRANSACTION_TABLE t JOIN USER_TABLE u ON t.userID = u.ID JOIN PRODUCT_TABLE p ON p.ID = t.productID WHERE u.ID = %d", Objects.requireNonNull(Cache.getLoggedUser().getValue()).userID);

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);
        int total = cursor.getCount();
        Log.i("jumlahTotal", String.format("%d", total));

        return total;
    }
}
