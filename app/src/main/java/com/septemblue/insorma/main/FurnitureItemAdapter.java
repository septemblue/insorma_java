package com.septemblue.insorma.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.septemblue.insorma.databinding.MainFurnitureItemBinding;
import com.septemblue.insorma.local.Furniture;

import kotlin.Unit;

public class FurnitureItemAdapter extends ListAdapter<Furniture, FurnitureItemAdapter.FurnitureItemViewHolder>{
    ItemClickListener clickListener;

    protected FurnitureItemAdapter(ItemClickListener clickListener) {
        super(new FurnitureDiffCallback());
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FurnitureItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return FurnitureItemViewHolder.inflateFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureItemViewHolder holder, int position) {
        Furniture furniture = getItem(position);
        holder.bind(furniture, clickListener);
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
        public void bind(Furniture furniture, ItemClickListener clickListener) {
            binding.theTxt.setText("hello");
        }
    }

    interface ItemClickListener {
        Unit clickListener(Long furnitureId);
    }
}
