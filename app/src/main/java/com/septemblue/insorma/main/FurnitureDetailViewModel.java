package com.septemblue.insorma.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Product;
import com.septemblue.insorma.local.Transaction;
import com.septemblue.insorma.local.Users;

import java.util.Calendar;
import java.util.Objects;

public class FurnitureDetailViewModel extends ViewModel {

    // message live data
    private MutableLiveData<String> _furnitureDetailMessage = new MutableLiveData<>("");
    LiveData<String> furnitureDetailMessage = _furnitureDetailMessage;

    // function to buy, only buy when validation is succeeded
    public void buy(String mQuantity, Product checkedOutProduct) {
        int quantity = Integer.parseInt(mQuantity);
        boolean valid = validate(quantity);

        if (valid) {
            Database.setTransId(Database.getTransId() + 1);
            Users user = Users.getAccount(Database.accounts.getValue(), Cache.getLoggedUser().getValue());
            Objects.requireNonNull(Database.getTransactionHistory().getValue())
                    .add(new Transaction(Database.getTransId(), user, quantity, checkedOutProduct, checkedOutProduct.price * quantity, Calendar.getInstance().getTime()));
        }
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

}