// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all the furnitures items available in database
    and directing user to the detail if user click the more button on the items card

    for improvement notes :
    - later i think i should implement more shared view model
    - i used staggeredGrid layout manager to display the furnitures, but i should've use
    linear horizontal like commit on 29 march, and get more linear recycle view for each
    furniture category

 */
package com.septemblue.insorma.main;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.septemblue.insorma.databinding.FragmentHomeBinding;
import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.main.api.FurnituresAPI;
import com.septemblue.insorma.main.dataclass.Furniture;
import com.septemblue.insorma.main.dataclass.Furnitures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// please read note above package
public class HomeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* handle the back press hardware button
        to prevent back to login without sign out or delete account */
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // View binding + view model
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        // Retrofit
        // base url : https://mocki.io
        // api endpoint : /v1/5f379081-2473-4494-9cc3-9e808772dc54

        // get furnitures
        // PENTING BIAR RAME = kalau balik ke fragment pake cache jangan api call lagi.
        if (Cache.furnitures.size() <= 0) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit =  new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://mocki.io")
                    .build();

            FurnituresAPI service = retrofit.create(FurnituresAPI.class);
            Call<Furnitures> response = service.listFurnitures();
            response.enqueue(new Callback<Furnitures>() {
                @Override
                public void onResponse(Call<Furnitures> call, Response<Furnitures> response) {
                    viewModel.setFurnitures(response.body().getFurnitures());
                    if(!viewModel.makeAdapter()) {
                        binding.noFurniture.setText("There are no furniture");
                        Snackbar.make(view, "There are no furniture", Snackbar.LENGTH_SHORT).show();
                    }
                    binding.furnitureList.setAdapter(viewModel.getAdapter());
                }

                @Override
                public void onFailure(Call<Furnitures> call, Throwable t) {
                    Log.i("respon", t.getMessage());
                }
            });
        } else {
            if(!viewModel.makeAdapter()) {
                binding.noFurniture.setText("There are no furniture");
                Snackbar.make(view, "There are no furniture", Snackbar.LENGTH_SHORT).show();
            }
            binding.furnitureList.setAdapter(viewModel.getAdapter());
        }




        return view;
    }

}