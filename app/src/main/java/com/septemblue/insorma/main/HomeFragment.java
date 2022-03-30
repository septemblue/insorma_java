// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all the furnitures items available in database
    and directing user to the detail if user click the more button on the items card

    in this activity scope uses :
    - Navigation Fragment dependency ( tried to keep all login system as 1 scope by 1 activity)
    - Lifecycle ViewModel dependency ( tried to emphasize separation of concern between logic and view)
    - Lifecycle LiveData dependency  ( tried to implement reactive programming )
    - buildFeatures UI layer library View Binding ( simplify findViewById yet provides Null safety
    and type safety)


    for improvement notes :
    - later i think i should use shared view model for register and login
    - in the register view model, i implement shortcut way like a tape
    to solve something not exist in sdk23 and of course it is smelly to bugs
    later on could use solid implementation.

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

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        FurnitureItemAdapter adapter = new FurnitureItemAdapter();
        if (Database.furnitures.getValue().size() != 0) {
            adapter.submitList(Database.furnitures.getValue());
        } else {
            binding.noFurniture.setText("There are no furniture");
            Snackbar.make(view, "There are no furniture", Snackbar.LENGTH_SHORT).show();
        }
        binding.furnitureList.setAdapter(adapter);
        return view;
    }

}