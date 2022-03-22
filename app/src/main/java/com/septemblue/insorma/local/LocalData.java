package com.septemblue.insorma.local;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

public class LocalData {
    private static MutableLiveData<String> _loggedUser = new MutableLiveData<>("");
    static LiveData<String> loggedUser;

    // return immutable live data ;
    public static LiveData<String> getLoggedUser() { return _loggedUser; }

    // set logged user  only if user exist in database else throw // maybe later can catch it and give pop up window
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void setLoggedUser(String userEmail) {
        if (Objects.requireNonNull(Database.accounts.getValue()).containsKey(userEmail)) {
            _loggedUser.setValue(userEmail);
        } else {
            throw new RuntimeException("user does not exist");
        }
    }

    private static MutableLiveData<String> _checkedOutFurniture = new MutableLiveData<>("");
    static LiveData<String> checkedOutFurniture;

    // return immutable live data
    static LiveData<String> getCheckedOutFurniture() {
        return _checkedOutFurniture;
    }

    // set checkedOut furniture only if it exist in database else throw
    @RequiresApi(api = Build.VERSION_CODES.N)
    void setCheckedOutFurniture(String furnitureId) {
        if (Objects.requireNonNull(Database.furnitures.getValue()).stream().anyMatch(it -> it.id.equals(furnitureId))) {
            _checkedOutFurniture.setValue(furnitureId);
        }else {
            throw new RuntimeException("furniture does not exist");
        }
    }

}
