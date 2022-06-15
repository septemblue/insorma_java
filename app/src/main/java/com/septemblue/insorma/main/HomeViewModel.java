package com.septemblue.insorma.main;

import static com.septemblue.insorma.local.Cache.furnitures;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.main.dataclass.Furniture;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // for future improvements
    public void setFurnitures(ArrayList<Furniture> furnitures) {
        Cache.furnitures = new ArrayList<>(furnitures);
    }

    // create the adapter for home recycler view
    // give adapter the list if not empty or give empty warning
    private FurnitureItemAdapter adapter;
    public boolean makeAdapter() {
        adapter = new FurnitureItemAdapter();
        if (furnitures.size() != 0) {
            Log.i("furniture", furnitures.toString());
            adapter.submitList(furnitures);
            return true;
        }
        return false;
    }

    public FurnitureItemAdapter getAdapter() {
        return adapter;
    }
}