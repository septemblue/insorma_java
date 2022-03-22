package com.septemblue.insorma.sign;

import android.os.Build;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Account;
import com.septemblue.insorma.local.Database;

public class RegisterViewModel extends ViewModel {

    //message for register fragment
    private MutableLiveData<String> _registerMessage = new MutableLiveData<>("");
    LiveData<String> registerMessage = _registerMessage;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void register(
            EditText emailAddress,
            EditText username,
            EditText phoneNumber,
            EditText password
    ) {
        if (privateIsEmpty(emailAddress, username, phoneNumber, password)) {
            _registerMessage.setValue("All fields must be filled");
            return;
        }
        boolean valid = validate(emailAddress.getText().toString(), username.getText().toString(), password.getText().toString());
        if (valid) {
            _register(emailAddress.getText().toString(), username.getText().toString(), password.getText().toString());
        }
    }

    private void _register(String emailAddress, String username, String password) {
        Account newAccount = new Account(emailAddress, username, password);
        newAccount.password = password;
        Database.accounts.getValue().put(emailAddress, newAccount);
        _registerMessage.setValue("Register succeed");
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean validate(
            String emailAddress,
            String username,
            String password
    ) {
        if (!emailAddress.endsWith(".com")) {
            _registerMessage.setValue("email address must end with .com");
            return false;
        } else if (username.length() < 3 || username.length() > 20) {
            _registerMessage.setValue("username must between 3 to 20 character");
            return false;
        } else if (password.chars().allMatch(Character::isLetterOrDigit)) {
            _registerMessage.setValue("password must contain number and alphabets");
            return false;
        } else if (Database.accounts.getValue().containsKey(emailAddress)) {
            _registerMessage.setValue("email address already exist");
            return false;
        } else if (Database.accounts.getValue().values().stream().anyMatch(it -> it.username.equals(username))) {
            _registerMessage.setValue("username already exist");
            return false;
        }
        return true;
    }

    // because so many things to check, i want to shorten with varargs XD
    private boolean privateIsEmpty(EditText ... args) {
        for (EditText it : args) {
            if (it.getText().toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}