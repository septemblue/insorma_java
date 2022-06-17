package com.septemblue.insorma.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Product;
import com.septemblue.insorma.local.Transaction;
import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.main.dataclass.Furniture;
import com.septemblue.insorma.storage.ProductHelper;
import com.septemblue.insorma.storage.ProductModel;
import com.septemblue.insorma.storage.TransactionHelper;
import com.septemblue.insorma.storage.TransactionModel;
import com.septemblue.insorma.storage.UserHelper;
import com.septemblue.insorma.storage.UserModel;

import java.util.Calendar;
import java.util.Objects;

public class FurnitureDetailViewModel extends ViewModel {

    // message live data
    private MutableLiveData<String> _furnitureDetailMessage = new MutableLiveData<>("");
    LiveData<String> furnitureDetailMessage = _furnitureDetailMessage;


    // function to buy, only buy when validation is succeeded
    public void buy(String mQuantity, Furniture furniture, TransactionHelper transactionHelper) {
        int quantity = Integer.parseInt(mQuantity);
        boolean valid = validate(quantity);
        Cache.setCheckedProduct(furniture);

        if (valid) {
            UserModel user = Cache.getLoggedUser().getValue();
            ProductModel product = Cache.getCheckedProduct().getValue();
            String transactionDate = Calendar.getInstance().getTime().toString();

            TransactionModel transaction = new TransactionModel(user.userID, product.productID, transactionDate, quantity);
            Log.i("transak", transaction.toString());
            transactionHelper.addTransaction(transaction);
        }

        sendSMS(furniture, valid);
    }

    // validate the quantity
    private boolean validate(int quantity) {
        if (quantity <= 0) {
            _furnitureDetailMessage.setValue("Quantity must greater than 0");
            return false;
        }
        _furnitureDetailMessage.setValue("Thanks for purchasing");
        return true;
    }

    SmsManager smsManager = SmsManager.getDefault();

    private void sendSMS(Furniture furniture, boolean valid) {
        // PIXEL 3 API 23 PORT 5554
        if (valid) {
            smsManager.sendTextMessage("5554", null, "has been successfully buying " + furniture.getProduct_name(), null, null);
        } else {
            smsManager.sendTextMessage("5554", null, "Error to buy " + furniture.getProduct_name(), null, null);

        }
    }

}