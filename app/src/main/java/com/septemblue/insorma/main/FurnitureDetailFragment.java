// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all the details from furniture selected in
    home fragment which user can also buy by defining quantity and click buy button

    for improvement notes :
    - later i think i should implement more shared view model

 */
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
import android.widget.Toast;

import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentFurnitureDetailBinding;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Furniture;
import com.septemblue.insorma.local.LocalData;

import java.util.Objects;
// please read note above package
public class FurnitureDetailFragment extends Fragment {

    private FurnitureDetailViewModel viewModel;
    private FragmentFurnitureDetailBinding binding;

    // has option menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // binding + view model
        binding = FragmentFurnitureDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(FurnitureDetailViewModel.class);

        // get selected furniture from home fragment by safeargs
        Furniture checkedOutFurniture = Furniture
                .getFurniture(Database.furnitures.getValue(), FurnitureDetailFragmentArgs.fromBundle(requireArguments()).getFurnitureId());
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(checkedOutFurniture.title);

        // give layout the furniture data
        binding.furnitureDetailImage.setImageResource(checkedOutFurniture.imageSource);
        binding.furnitureDetailTitle.setText(checkedOutFurniture.title);
        binding.furnitureDetailPrice.setText(String.format("Rp. %s", checkedOutFurniture.price));

        // button to buy
        binding.furnitureDetailBuyButton.setOnClickListener(it -> {
            viewModel.buy(binding.furnitureDetailQuantity.getText().toString(), checkedOutFurniture);
            viewModel.furnitureDetailMessage.observe(getViewLifecycleOwner(), newValue -> {
                Toast.makeText(getContext(), newValue, Toast.LENGTH_SHORT).show();
            });
        });
        return view;
    }

    // hide username from toolbar
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.toolbar_username);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}