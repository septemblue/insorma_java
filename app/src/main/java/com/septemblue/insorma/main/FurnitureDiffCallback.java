package com.septemblue.insorma.main;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.septemblue.insorma.local.Furniture;

// DiffUtilCallback from Recycler view to track layout changes
public class FurnitureDiffCallback extends DiffUtil.ItemCallback<Furniture> {
    @Override
    public boolean areItemsTheSame(@NonNull Furniture oldItem, @NonNull Furniture newItem) {
        return oldItem == newItem;
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Furniture oldItem, @NonNull Furniture newItem) {
        return oldItem == newItem;
    }
}
