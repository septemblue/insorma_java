package com.septemblue.insorma.sign;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Cache;

import java.util.HashMap;
import java.util.Objects;

public class LoginViewModel extends ViewModel {
    // can only set here when user success to login
    private final MutableLiveData<Boolean> _logged = new MutableLiveData<>(false);
    LiveData<Boolean> logged = _logged;

    // message for login fragment
    private final MutableLiveData<String> _loginMessage = new MutableLiveData<>("");
    LiveData<String> loginMessage = _loginMessage;

    // validate the edit texts and validate the data
    public void login(EditText emailAddress, EditText password) {
        // implement isBlank idk why i cant access the function
        if (emailAddress.getText().toString().trim().isEmpty()) {
            _loginMessage.setValue("All fields must be filled");
            return;
        }
        boolean valid = validate(emailAddress.getText().toString(), password.getText().toString());
        if (valid) { Cache.setLoggedUser(emailAddress.getText().toString()); }
    }

    // required validations
    // 1. if the account exist
    // 2. if the password same as the account
    private boolean validate(String emailAddress, String password) {
        HashMap<String, Users> accounts = Database.accounts.getValue();
        if (!accounts.containsKey(emailAddress)) {
            _loginMessage.setValue("email not found");
            return false;
        } else if (!Objects.requireNonNull(accounts.get(emailAddress)).password.equals(password)) {
            _loginMessage.setValue("wrong password");
            return false;
        }
        _loginMessage.setValue("Welcome " + Objects.requireNonNull(accounts.get(emailAddress)).username);
        _logged.setValue(true);
        return true;
    }
}