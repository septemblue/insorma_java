package com.septemblue.insorma.main;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentHomeBinding;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Furniture;
import com.septemblue.insorma.local.LocalData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;

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
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.furnitureListview.setAdapter(new FurnitureAdapter(this.getContext()));
        return view;
    }

    private class FurnitureAdapter extends BaseAdapter {
        Context context;
        public FurnitureAdapter(Context context) {
            this.context = context;
        }


        @Override
        public int getCount() {
            int total = Database.furnitures.getValue().size();
            if (total != 0) return total;
            else {
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "There are no furniture", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Close", it -> {}).show();
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View adapterView = getLayoutInflater().inflate(R.layout.main_furnitures_listview, parent, false);
            ImageView furnitureImage = adapterView.findViewById(R.id.furniture_image);
            TextView furnitureTitle = adapterView.findViewById(R.id.furniture_title);
            TextView furniturePrice = adapterView.findViewById(R.id.furniture_price);
            Button furnitureDetail = adapterView.findViewById(R.id.furniture_detail);

            ArrayList<Furniture> furnitures = Database.furnitures.getValue();
            String furnitureId = furnitures.get(position).id;
            furnitureImage.setImageResource(furnitures.get(position).imageSource);
            furnitureTitle.setText(furnitures.get(position).title);
            furniturePrice.setText(String.format("Rp. %s", furnitures.get(position).price));

            furnitureDetail.setOnClickListener(it -> {
                LocalData.setCheckedOutFurniture(furnitureId);
                Navigation.findNavController(adapterView).navigate(R.id.furnitureDetailFragment);
            });

            return adapterView;
        }
    }


}