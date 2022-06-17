package com.septemblue.insorma.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.septemblue.insorma.main.dataclass.Furniture;
import com.septemblue.insorma.storage.DatabaseHelper;
import com.septemblue.insorma.storage.ProductHelper;
import com.septemblue.insorma.storage.ProductModel;
import com.septemblue.insorma.storage.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cache {
    private static MutableLiveData<UserModel> _loggedUser = new MutableLiveData<>();
    static LiveData<UserModel> loggedUser = _loggedUser;

    // return immutable live data ;
    public static LiveData<UserModel> getLoggedUser() {
        return loggedUser; }

    // set logged user  only if user exist in database else throw // maybe later can catch it and give pop up window
    public static void setLoggedUser(String userEmail, List<UserModel> users) {

        UserModel loggedUser = null;
        boolean validEmail = false;
        for (UserModel user :
                users) {
            if (user.emailAddress.equals(userEmail)) {
                validEmail = true;
                loggedUser = user;
            }
        }

        if (validEmail || userEmail.equals("")) {
            _loggedUser.setValue(loggedUser);
        } else {
            throw new RuntimeException("user does not exist");
        }
    }
    public static ArrayList<Furniture> furnitures = new ArrayList<>();


    private static MutableLiveData<ProductModel> _checkedProduct = new MutableLiveData<>();
    static LiveData<ProductModel> checkedProduct = _checkedProduct;

    public static LiveData<ProductModel> getCheckedProduct() {
        return checkedProduct;
    }

    public static void setCheckedProduct(Furniture furniture) {
        ProductModel theProduct = null;
        boolean valid = false;
        for (ProductModel product :
                ProductHelper.products) {
            if (product.productName.equals(furniture.getProduct_name())) {
                theProduct = product;
                valid = true;
            }
        }

        if (valid) {
            _checkedProduct.setValue(theProduct);
        } else {
            throw new RuntimeException("user does not exist");
        }
    }
}
