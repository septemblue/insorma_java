package com.septemblue.insorma.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.septemblue.insorma.storage.DatabaseHelper;
import com.septemblue.insorma.storage.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cache {
    private static MutableLiveData<String> _loggedUser = new MutableLiveData<>("");
    static LiveData<String> loggedUser;

    // return immutable live data ;
    public static LiveData<String> getLoggedUser() { return _loggedUser; }

    // set logged user  only if user exist in database else throw // maybe later can catch it and give pop up window
    public static void setLoggedUser(String userEmail, DatabaseHelper databaseHelper) {

        // get data from database
        String getAllAccountsQuery = "SELECT * FROM SIGN_TABLE";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);
        boolean validemail = false;

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL_ADDRESS"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
                String dbpassword = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("PHONE_NUMBER"));

                if (userEmail.equals(email)) validemail = true;
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        cursor.close();
        db.close();

        if (validemail || userEmail.equals("")) {
            _loggedUser.setValue(userEmail);
        } else {
            throw new RuntimeException("user does not exist");
        }
    }
}
