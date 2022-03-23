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
    private MutableLiveData<String> _furnitureDetailMessage = new MutableLiveData<>("");
    LiveData<String> furnitureDetailMessage = _furnitureDetailMessage;

    public void buy(String mQuantity, Furniture checkedOutFurniture) {
        int quantity = Integer.parseInt(mQuantity);
        boolean valid = validate(quantity);

        if (valid) {
            History.transId += 1;
            Objects.requireNonNull(Database.transactionHistory.getValue())
                    .add(new History(quantity, checkedOutFurniture, checkedOutFurniture.price * quantity, Calendar.getInstance().getTime()));
        }
    }

    private boolean validate(int quantity) {
        if (quantity <= 0) {
            _furnitureDetailMessage.setValue("Quantity must greater than 0");
            return false;
        }
        _furnitureDetailMessage.setValue("Thanks for purchasing");
        return true;
    }

}