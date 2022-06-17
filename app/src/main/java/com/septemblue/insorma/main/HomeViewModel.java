package com.septemblue.insorma.main;

import static com.septemblue.insorma.local.Cache.furnitures;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.main.dataclass.Furniture;
import com.septemblue.insorma.storage.ProductHelper;
import com.septemblue.insorma.storage.ProductModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // for future improvements
    public void setFurnitures(ArrayList<Furniture> furnituress, ProductHelper productHelper) {
        furnitures = new ArrayList<>(furnituress);
        Log.i("logi", String.format("%d", productHelper.getProducts().size()));
        if (productHelper.getProducts().size() <= 0) {
            for (Furniture furniture :
                    furnitures) {
                ProductModel product = new ProductModel(furniture.getProduct_name(), furniture.getPrice(), furniture.getImage(), furniture.getDescription());
                productHelper.insertProduct(product);
            }
        }
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