package com.septemblue.insorma.main;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentFurnitureDetailBinding;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Furniture;
import com.septemblue.insorma.local.LocalData;

import java.util.Objects;

public class FurnitureDetailFragment extends Fragment {

    private FurnitureDetailViewModel viewModel;
    private FragmentFurnitureDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFurnitureDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(FurnitureDetailViewModel.class);
        Furniture checkedOutFurniture = Furniture.getFurniture(Database.furnitures.getValue(), LocalData.getCheckedOutFurniture().getValue());

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(checkedOutFurniture.title);

        binding.furnitureDetailImage.setImageResource(checkedOutFurniture.imageSource);
        binding.furnitureDetailTitle.setText(checkedOutFurniture.title);
        binding.furnitureDetailPrice.setText(String.format("Rp. %s", checkedOutFurniture.price));

        binding.furnitureDetailBuyButton.setOnClickListener(it -> {
            viewModel.buy(binding.furnitureDetailQuantity.getText().toString(), checkedOutFurniture);
        });
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.toolbar_username);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}