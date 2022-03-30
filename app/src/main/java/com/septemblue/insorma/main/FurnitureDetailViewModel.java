package com.septemblue.insorma.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Furniture;
import com.septemblue.insorma.local.History;
import com.septemblue.insorma.local.LocalData;

import java.util.Calendar;
import java.util.Objects;

public class FurnitureDetailViewModel extends ViewModel {
    // has static id that will to show how many transactions already done
    private static int transId = 0;

    // message live data
    private MutableLiveData<String> _furnitureDetailMessage = new MutableLiveData<>("");
    LiveData<String> furnitureDetailMessage = _furnitureDetailMessage;

    // function to buy, only buy when validation is succeeded
    public void buy(String mQuantity, Furniture checkedOutFurniture) {
        int quantity = Integer.parseInt(mQuantity);
        boolean valid = validate(quantity);

        if (valid) {
            transId += 1;
            Objects.requireNonNull(Database.transactionHistory.getValue())
                    .add(new History(transId, quantity, checkedOutFurniture, checkedOutFurniture.price * quantity, Calendar.getInstance().getTime()));
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