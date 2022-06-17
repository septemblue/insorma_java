package com.septemblue.insorma.main;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.storage.UserHelper;
import com.septemblue.insorma.storage.UserModel;

import java.util.HashMap;

public class ProfileViewModel extends ViewModel {

    // get live datas for reactive programming
    // when account deleted, username changed, and new message notificiation
    // outsider only can access the live data, so only this view model can change the value
    private MutableLiveData<Boolean> _accountDeleted = new MutableLiveData<>(false);
    LiveData<Boolean> accountDeleted = _accountDeleted;

    private MutableLiveData<Boolean> _usernameChanged = new MutableLiveData<>(false);
    LiveData<Boolean> usernameChanged = _usernameChanged;

    private MutableLiveData<String> _profileMessage = new MutableLiveData<>("");
    LiveData<String> profileMessage = _profileMessage;

    // edit the username if validation succeeded
    public void editUsername(EditText newUsername, HashMap<String, Users> accounts, UserHelper userHelper) {
        boolean valid = validate(newUsername.getText().toString(), accounts);
        if (valid) {
            _editUsername(newUsername.getText().toString(), userHelper);
            _usernameChanged.setValue(true);
        }
    }

    private void _editUsername(String newUsername, UserHelper userHelper) {
        userHelper.editUsers(Cache.getLoggedUser().getValue().userID, newUsername);
        Cache.setLoggedUser(Cache.getLoggedUser().getValue().emailAddress, userHelper.getUsers());
    }

    private boolean validate(String newUsername, HashMap<String, Users> accounts) {
        if (newUsername.length() < 3 || newUsername.length() > 20) {
            _profileMessage.setValue("username must between 3 and 20 character");
            return false;
        } else if (Users.any(accounts, newUsername)) {
            _profileMessage.setValue("username already exist");
            return false;
        }
        _profileMessage.setValue("welcome " + newUsername);
        return true;
    }

    public boolean deleteAccount(UserHelper userHelper) {
        boolean deleted = _deleteAccount(userHelper);
        if (deleted) {
            _profileMessage.setValue("Account Deleted");
            return true;
        }
        _profileMessage.setValue("Failed to delete account");
        return false;
    }

    private boolean _deleteAccount(UserHelper userHelper) {
        for (UserModel user :
                UserHelper.users) {
            if (user.userID == Cache.getLoggedUser().getValue().userID) {
                userHelper.deleteUsers(Cache.getLoggedUser().getValue().userID);
                UserHelper.users.remove(user);
            }
        }
        if (userHelper.deleteUsers(Cache.getLoggedUser().getValue().userID)) {
            userHelper.updateUserList();
            _accountDeleted.setValue(true);
            return true;
        }
        return false;
    }

}