package com.septemblue.insorma.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.septemblue.insorma.databinding.MainFurnitureItemBinding;
import com.septemblue.insorma.local.Furniture;

import kotlin.Unit;

public class FurnitureItemAdapter extends ListAdapter<Furniture, FurnitureItemAdapter.FurnitureItemViewHolder>{

    protected FurnitureItemAdapter() {
        super(new FurnitureDiffCallback());
    }

    @NonNull
    @Override
    public FurnitureItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("kudanil", "iamexist");
        return FurnitureItemViewHolder.inflateFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureItemViewHolder holder, int position) {
        Furniture furniture = getItem(position);
        holder.bind(furniture);
    }


    static class FurnitureItemViewHolder extends RecyclerView.ViewHolder {
        static MainFurnitureItemBinding binding;

        public FurnitureItemViewHolder(@NonNull MainFurnitureItemBinding binding) {
            super(binding.getRoot());
        }

        static FurnitureItemViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            binding = MainFurnitureItemBinding.inflate(layoutInflater, parent, false);
            return new FurnitureItemViewHolder(binding);
        }

        public void bind(Furniture furniture) {
            binding.furnitureImage.setImageResource(furniture.imageSource);
            binding.furnitureTitle.setText(furniture.title);
            binding.furniturePrice.setText(String.format("Rp: %s", furniture.price));
//          Here should only contain view logic, but passing 2 paremeter for OnClicklistener by lambda still confused me
//          so for now i just implement business logic here hahaha. i'll improve later
            binding.furnitureDetail.setOnClickListener(it -> {
                Toast.makeText(this.itemView.getContext(), "you clicked " + furniture.id, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
