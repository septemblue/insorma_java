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