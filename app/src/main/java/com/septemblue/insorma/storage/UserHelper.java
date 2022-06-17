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

import com.septemblue.insorma.local.Cache;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public static List<UserModel> users = new ArrayList<>();

    public UserHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public boolean register(String email, String username, String password, String phone) {

        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL_ADDRESS, email);
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_PHONE_NUMBER, phone);

        long insert = db.insert("USER_TABLE", null, contentValues);
        if (insert != -1) updateUserList();

        return insert != -1;
    }

    public void updateUserList() {

        String getAllAccountsQuery = "SELECT * FROM USER_TABLE";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int userID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL_ADDRESS"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
                String dbpassword = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("PHONE_NUMBER"));

                UserModel userModel = new UserModel(email, username, dbpassword, phone);
                userModel.userID = userID;
                users.add(userModel);

                Log.i("model", userModel.toString());
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }
        cursor.close();
        db.close();
    }

    public List<UserModel> getUsers() {
        updateUserList();
        return users;
    }

    public void editUsers(int userID, String username) {
        db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(userID)};

        contentValues.put(COLUMN_USERNAME, username);
        db.update("USER_TABLE", contentValues, where, whereArgs);
    }

    public boolean deleteUsers(int userID) {
        db = databaseHelper.getWritableDatabase();
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(userID)};
        long del = db.delete("USER_TABLE", where, whereArgs);
        return del != -1;
    }
}
