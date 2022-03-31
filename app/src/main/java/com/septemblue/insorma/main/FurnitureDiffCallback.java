package com.septemblue.insorma.main;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.septemblue.insorma.local.Product;

// DiffUtilCallback from Recycler view to track layout changes
public class FurnitureDiffCallback extends DiffUtil.ItemCallback<Product> {
    @Override
    public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem == newItem;
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem == newItem;
    }
}
