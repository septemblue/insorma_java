package com.septemblue.insorma.sign;

import android.os.Build;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Account;
import com.septemblue.insorma.local.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class LoginViewModel extends ViewModel {
    // can only set here when user success to login
    private MutableLiveData<Boolean> _logged = new MutableLiveData<>(false);
    LiveData<Boolean> logged;
    LiveData<Boolean> getLogged() {return _logged;}

    // message for login fragment
    private MutableLiveData<String> _loginMessage = new MutableLiveData<>("");
    LiveData<String> loginMessage;
    LiveData<String> getLoginMessage() {return _loginMessage;}

    // validate the edit texts and validate the data
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String login(EditText emailAddress, EditText password) {
        // implement isBlank idk why i cant access the function
        if (emailAddress.getText().toString().trim().isEmpty()) {
            _loginMessage.setValue("All fields must be filled");
            return "";
        }
        boolean valid = validate(emailAddress.getText().toString(), password.getText().toString());
        return "";
    }

    // required validations
    // 1. if the account exist
    // 2. if the password same as the account
    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean validate(String emailAddress, String password) {
        HashMap<String, Account> accounts = Database.accounts.getValue();

        assert accounts != null;
        if (!accounts.containsKey(emailAddress)) {
            _loginMessage.setValue("email not found");
            return false;
        } else if (Objects.requireNonNull(accounts.get(emailAddress)).password.equals(password)) {
            _loginMessage.setValue("wrong password");
            return false;
        }
        _loginMessage.setValue("Welcome " + Objects.requireNonNull(accounts.get(emailAddress)).username);
        return true;
    }
}