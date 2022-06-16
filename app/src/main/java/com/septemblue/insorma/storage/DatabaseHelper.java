package com.septemblue.insorma.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "insorma db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String userTable = "CREATE TABLE USER_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EMAIL_ADDRESS + " TEXT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT)";
        sqLiteDatabase.execSQL(userTable);

        String productTable = "CREATE TABLE PRODUCT_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, productName TEXT, productPrice TEXT, productImage TEXT, productDescription TEXT)";
        sqLiteDatabase.execSQL(productTable);

        String transactionTable = "CREATE TABLE TRANSACTION_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, userID INT, productID INT,transactionDate TEXT, quantity INT, FOREIGN KEY(userID) REFERENCES USER_TABLE(ID), FOREIGN KEY(productID) REFERENCES PRODUCT_TABLE(ID))";
        sqLiteDatabase.execSQL(transactionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
