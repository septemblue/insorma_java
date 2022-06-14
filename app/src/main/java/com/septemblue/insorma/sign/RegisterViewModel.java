package com.septemblue.insorma.sign;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.storage.DatabaseHelper;
import com.septemblue.insorma.storage.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RegisterViewModel extends ViewModel {

    //message for register fragment
    private MutableLiveData<String> _registerMessage = new MutableLiveData<>("");
    LiveData<String> registerMessage = _registerMessage;

    public void register(
            EditText emailAddress,
            EditText username,
            EditText phoneNumber,
            EditText password,
            DatabaseHelper databaseHelper) {
        if (privateIsBlank(emailAddress, username, phoneNumber, password)) {
            _registerMessage.setValue("All fields must be filled");
            return;
        }
        boolean valid = validate(emailAddress.getText().toString(), username.getText().toString(), password.getText().toString());
        if (valid) {
            _register(emailAddress.getText().toString(), username.getText().toString(), password.getText().toString(), phoneNumber.getText().toString() ,databaseHelper);
        }
    }

    private void _register(String emailAddress, String username, String password, String phone, DatabaseHelper databaseHelper) {
        databaseHelper.register(emailAddress, username, password, phone);
        _registerMessage.setValue("Register succeed");
    }


    private boolean validate(
            String emailAddress,
            String username,
            String password) {

        List<UserModel> returnList = new ArrayList<>();

        // get data from database
        String getAllAccountsQuery = "SELECT * FROM SIGN_TABLE";

        if (!emailAddress.endsWith(".com")) {
            _registerMessage.setValue("email address must end with .com");
            return false;
        } else if (username.length() < 3 || username.length() > 20) {
            _registerMessage.setValue("username must between 3 to 20 character");
            return false;
        } else if (!isAlphanumeric(password)) {
            _registerMessage.setValue("password must contain number and alphabets");
            return false;
        } else if (Database.accounts.getValue().containsKey(emailAddress)) {
            _registerMessage.setValue("email address already exist");
            return false;
        } else if (Users.any(Database.accounts.getValue(), username)) {
            _registerMessage.setValue("username already exist");
            return false;
        }

        return true;
    }

    // private methods to implement somehting not exist in api 23 or java
    // because theres no isblank and the calllength is too long so i made this xd
    private boolean privateIsBlank(EditText ... args) {
        for (EditText it : args) {
            if (it.getText().toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // just random implementation hahaha
    private boolean isAlphanumeric(String sequence) {
        char[] charArray = sequence.toCharArray();
        boolean isLetterOnly = true;
        boolean isDigitOnly = true;
        for (char c :
                charArray) {
            if (Character.isDigit(c)) {
                isLetterOnly = false;
            }
        }
        for (char c :
                charArray) {
            if (Character.isLetter(c)) {
                isDigitOnly = false;
            }
        }
        if (!isDigitOnly && !isLetterOnly) {
            return true;
        }
        return false;
    }
}