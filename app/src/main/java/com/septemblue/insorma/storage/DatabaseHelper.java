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

        String createTable = "CREATE TABLE SIGN_TABLE (" + COLUMN_EMAIL_ADDRESS + " TEXT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT)";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean admin() {
        // add admin
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL_ADDRESS, "admin");
        contentValues.put(COLUMN_USERNAME, "admin");
        contentValues.put(COLUMN_PASSWORD, "admin");
        contentValues.put(COLUMN_PHONE_NUMBER, "admin");

        long insert = db.insert("SIGN_TABLE", null, contentValues);
        return insert != -1;
    }

    public boolean register(String email, String username, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL_ADDRESS, email);
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_PHONE_NUMBER, phone);

        long insert = db.insert("SIGN_TABLE", null, contentValues);
        return insert != -1;
    }
}
