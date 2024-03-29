package com.septemblue.insorma.sign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.storage.DatabaseHelper;
import com.septemblue.insorma.storage.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LoginViewModel extends ViewModel {
    // can only set here when user success to login
    private final MutableLiveData<Boolean> _logged = new MutableLiveData<>(false);
    LiveData<Boolean> logged = _logged;

    // message for login fragment
    private final MutableLiveData<String> _loginMessage = new MutableLiveData<>("");
    LiveData<String> loginMessage = _loginMessage;

    // validate the edit texts and validate the data
    public void login(EditText emailAddress, EditText password, List<UserModel> users) {
        // implement isBlank idk why i cant access the function
        if (emailAddress.getText().toString().trim().isEmpty()) {
            _loginMessage.setValue("All fields must be filled");
            return;
        }

        // validate
        boolean valid = validate(emailAddress.getText().toString(), password.getText().toString(),  users);
        if (valid) { Cache.setLoggedUser(emailAddress.getText().toString(), users); }
    }

    // required validations
    // 1. if the account exist
    // 2. if the password same as the account
    private boolean validate(String emailAddress, String password, List<UserModel> users) {

        boolean login = false;
        boolean emailPassword = false;
        UserModel loggedUser = null;

        for (UserModel x:
             users) {
            if (x.emailAddress.equals(emailAddress)) {
                login = true;
                if (x.password.equals(password)) {
                    emailPassword = true;
                    loggedUser = x;
                }
            }
        }

        if (!login) {
            _loginMessage.setValue("email not found");
            return false;
        } else if (!emailPassword) {
            _loginMessage.setValue("wrong password");
            return false;
        }

        _loginMessage.setValue("Welcome " + loggedUser.username);
        _logged.setValue(true);
        return true;
    }
}