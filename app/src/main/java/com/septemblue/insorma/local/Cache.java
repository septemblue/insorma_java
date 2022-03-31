package com.septemblue.insorma.local;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

public class Cache {
    private static MutableLiveData<String> _loggedUser = new MutableLiveData<>("");
    static LiveData<String> loggedUser;

    // return immutable live data ;
    public static LiveData<String> getLoggedUser() { return _loggedUser; }

    // set logged user  only if user exist in database else throw // maybe later can catch it and give pop up window
    public static void setLoggedUser(String userEmail) {
        if (Objects.requireNonNull(Database.accounts.getValue()).containsKey(userEmail) || userEmail.equals("")) {
            _loggedUser.setValue(userEmail);
        } else {
            throw new RuntimeException("user does not exist");
        }
    }
}
