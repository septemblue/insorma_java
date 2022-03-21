package com.septemblue.insorma.local;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LocalData {
    private MutableLiveData<String> _loggedUser = new MutableLiveData<>("");
    LiveData<String> loggedUser;

    // return immutable live data
    LiveData<String> getLoggedUser() {
        return _loggedUser;
    }

    // set logged user  only if user exist in database else throw // maybe later can catch it and give pop up window
    @RequiresApi(api = Build.VERSION_CODES.N)
    void setLoggedUser(String userEmail) {
        if (Database.accounts.getValue().stream().anyMatch(it -> it.emailAddress.equals(userEmail))) {
            _loggedUser.setValue(userEmail);
        } else {
            throw new RuntimeException("user does not exist");
        }
    }

    private MutableLiveData<String> _checkedOutFurniture = new MutableLiveData<>("");
    LiveData<String> checkedOutFurniture;

    // return immutable live data
    LiveData<String> getCheckedOutFurniture() {
        return _checkedOutFurniture;
    }

    // set checkedOut furniture only if it exist in database else throw
    @RequiresApi(api = Build.VERSION_CODES.N)
    void setCheckedOutFurniture(String furnitureId) {
        if (Database.furnitures.getValue().stream().anyMatch(it -> it.id.equals(furnitureId))) {
            _checkedOutFurniture.setValue(furnitureId);
        }else {
            throw new RuntimeException("furniture does not exist");
        }
    }

}
