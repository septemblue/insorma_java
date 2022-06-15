// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all the details from furniture selected in
    home fragment which user can also buy by defining quantity and click buy button

    for improvement notes :
    - later i think i should implement more shared view model

 */
package com.septemblue.insorma.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentFurnitureDetailBinding;
import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Product;
import com.septemblue.insorma.main.dataclass.Furniture;

import java.util.Objects;
// please read note above package
public class FurnitureDetailFragment extends Fragment {

    private FurnitureDetailViewModel viewModel;
    private FragmentFurnitureDetailBinding binding;
    ActivityResultLauncher<String> requestPermissionLauncher;

    // has option menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {

                    } else {

                    }
                });
        requestPermissionLauncher.launch(Manifest.permission.SEND_SMS);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // binding + view model
        binding = FragmentFurnitureDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(FurnitureDetailViewModel.class);

        // get selected furniture from home fragment by safeargs
        int checkedIndex = FurnitureDetailFragmentArgs.fromBundle(requireArguments()).getFurnitureIndex();
        Furniture checkedFurniture = Cache.furnitures.get(checkedIndex);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(checkedFurniture.getProduct_name());

        // give layout the furniture data
//        binding.furnitureDetailImage.setImageResource(furniture.imageSource);
        Glide.with(this.requireContext()).load(checkedFurniture.getImage()).into(binding.furnitureDetailImage);
        binding.furnitureDetailTitle.setText(checkedFurniture.getProduct_name());
        binding.furnitureDetailPrice.setText(String.format("Rp. %s", checkedFurniture.getPrice()));
        binding.furnitureDetailRating.setText(String.format("Rating : %s", checkedFurniture.getRating()));
        binding.furnitureDetailDescription.setText(checkedFurniture.getDescription());

        // button to buy
        binding.furnitureDetailBuyButton.setOnClickListener(it -> {
            viewModel.buy(binding.furnitureDetailQuantity.getText().toString(), checkedFurniture);
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