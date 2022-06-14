package com.septemblue.insorma.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.septemblue.insorma.databinding.MainFurnitureItemBinding;
import com.septemblue.insorma.main.dataclass.Furniture;

public class FurnitureItemAdapter extends ListAdapter<Furniture, FurnitureItemAdapter.FurnitureItemViewHolder>{

    // to update it's layout and data whenever the database changed, using DiffUtilCallback
    protected FurnitureItemAdapter() {
        super(new FurnitureDiffCallback());
    }

    @NonNull
    @Override
    public FurnitureItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // give the view
        return FurnitureItemViewHolder.inflateFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureItemViewHolder holder, int position) {
        // bind the view
        Furniture furniture = getItem(position);
        holder.bind(furniture, holder.itemView.getContext());
    }

    // this class used only to control all the view logic
    static class FurnitureItemViewHolder extends RecyclerView.ViewHolder {
        static MainFurnitureItemBinding binding;

        // getting the layout root
        public FurnitureItemViewHolder(@NonNull MainFurnitureItemBinding binding) {
            super(binding.getRoot());
        }
        // give the inflated object
        static FurnitureItemViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            binding = MainFurnitureItemBinding.inflate(layoutInflater, parent, false);
            return new FurnitureItemViewHolder(binding);
        }
        // binding, here i use non view logic here and should be improved later
        // but in kotlin project i already work all of the improvements in this project
        public void bind(Furniture furniture, Context context) {
            Glide.with(context).load(furniture.getImage()).into(binding.furnitureImage);
            binding.furnitureTitle.setText(furniture.getProduct_name());
            binding.furniturePrice.setText(furniture.getPrice());
            binding.furnitureRating.setText(furniture.getRating());
            binding.furnitureDescription.setText(furniture.getDescription());

//          Here should only contain view logic, but passing 2 paremeter for OnClicklistener by lambda still confused me
//          so for now i just implement business logic here hahaha. i'll improve later

            binding.furnitureDetail.setOnClickListener(it -> {
                HomeFragmentDirections.FurnitureMoreDetail action = HomeFragmentDirections.furnitureMoreDetail(furniture.id);
                Navigation.findNavController(itemView).navigate(action);
                Toast.makeText(this.itemView.getContext(), "you clicked " + furniture.id, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
