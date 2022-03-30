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

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentHomeBinding;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Furniture;
import com.septemblue.insorma.local.LocalData;

import org.w3c.dom.Text;

import java.util.ArrayList;
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

        // create the adapter for home recycler view
        // give adapter the list if not empty or give empty warning
        FurnitureItemAdapter adapter = new FurnitureItemAdapter();
        if (Database.furnitures.getValue().size() != 0) {
            adapter.submitList(Database.furnitures.getValue());
        } else {
            binding.noFurniture.setText("There are no furniture");
            Snackbar.make(view, "There are no furniture", Snackbar.LENGTH_SHORT).show();
        }

        // set the adapter
        binding.furnitureList.setAdapter(adapter);
        return view;
    }

}