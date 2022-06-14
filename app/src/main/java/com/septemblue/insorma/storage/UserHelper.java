package com.septemblue.insorma.storage;

import static com.septemblue.insorma.storage.DatabaseHelper.COLUMN_EMAIL_ADDRESS;
import static com.septemblue.insorma.storage.DatabaseHelper.COLUMN_PASSWORD;
import static com.septemblue.insorma.storage.DatabaseHelper.COLUMN_PHONE_NUMBER;
import static com.septemblue.insorma.storage.DatabaseHelper.COLUMN_USERNAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public static List<UserModel> users = new ArrayList<>();

    public UserHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
        admin();
    }

    public boolean admin() {
        // add admin
        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL_ADDRESS, "admin");
        contentValues.put(COLUMN_USERNAME, "admin");
        contentValues.put(COLUMN_PASSWORD, "admin");
        contentValues.put(COLUMN_PHONE_NUMBER, "admin");

        long insert = db.insert("SIGN_TABLE", null, contentValues);
        if (insert != -1) updateUserList();
        return insert != -1;
    }

    public boolean register(String email, String username, String password, String phone) {
        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL_ADDRESS, email);
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_PHONE_NUMBER, phone);

        long insert = db.insert("SIGN_TABLE", null, contentValues);
        if (insert != -1) updateUserList();

        return insert != -1;
    }

    public void updateUserList() {

        String getAllAccountsQuery = "SELECT * FROM SIGN_TABLE";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL_ADDRESS"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
                String dbpassword = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("PHONE_NUMBER"));

                UserModel userModel = new UserModel(email, username, dbpassword, phone);
                users.add(userModel);

                Log.i("model", userModel.toString());
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        cursor.close();
        db.close();
    }

}
