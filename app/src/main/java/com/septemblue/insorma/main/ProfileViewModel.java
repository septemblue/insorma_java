package com.septemblue.insorma.main;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Account;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.LocalData;

import java.util.HashMap;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> _accountDeleted = new MutableLiveData<>(false);
    LiveData<Boolean> accountDeleted = _accountDeleted;

    private MutableLiveData<Boolean> _usernameChanged = new MutableLiveData<>(false);
    LiveData<Boolean> usernameChanged = _usernameChanged;

    private MutableLiveData<String> _profileMessage = new MutableLiveData<>("");
    LiveData<String> profileMessage = _profileMessage;


    public void editUsername(EditText newUsername, HashMap<String, Account> accounts, Account user) {
        boolean valid = validate(newUsername.getText().toString(), accounts);
        if (valid) {
            _editUsername(newUsername.getText().toString(), user);
        }
    }

    private void _editUsername(String newUsername, Account user) {
        user.username = newUsername;
    }

    private boolean validate(String newUsername, HashMap<String, Account> accounts) {
        if (newUsername.length() < 3 || newUsername.length() > 20) {
            _profileMessage.setValue("username must between 3 and 20 character");
            return false;
        } else if (Account.any(accounts, newUsername)) {
            _profileMessage.setValue("username already exist");
            return false;
        }
        _profileMessage.setValue("welcome " + newUsername);
        return true;
    }

    public boolean deleteAccount(HashMap<String, Account> accounts, Account user) {
        boolean deleted = _deleteAccount(accounts, user);
        if (deleted) {
            _profileMessage.setValue("Account Deleted");
            return true;
        }
        _profileMessage.setValue("Failed to delete account");
        return false;
    }

    private boolean _deleteAccount(HashMap<String, Account> accounts, Account user) {
        if (accounts.containsValue(user)) {
            accounts.remove(user.emailAddress);
            _accountDeleted.setValue(true);
            return true;
        }
        return false;
    }

}