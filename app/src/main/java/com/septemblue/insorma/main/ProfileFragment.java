package com.septemblue.insorma.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.septemblue.insorma.databinding.FragmentProfileBinding;
import com.septemblue.insorma.local.Account;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.LocalData;
import com.septemblue.insorma.sign.SignActivity;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        Account user = Database.accounts.getValue().get(LocalData.getLoggedUser().getValue());

        binding.profileUsername.setText(user.username);
        binding.profileEmailAddress.setText(user.emailAddress);
        binding.profilePhoneNumber.setText(user.phoneNumber);

        binding.profileEditButton.setOnClickListener(it -> {
            binding.profileUsername.setVisibility(View.INVISIBLE);
            binding.profileEditUsername.setVisibility(View.VISIBLE);
            it.setVisibility(View.INVISIBLE);
            binding.profileSaveButton.setVisibility(View.VISIBLE);
        });

        binding.profileSaveButton.setOnClickListener(it -> {
            viewModel.editUsername(binding.profileEditUsername, Database.accounts.getValue(), user);
            viewModel.usernameChanged.observe(getViewLifecycleOwner(), newValue -> {
                if (newValue) {
                    binding.profileUsername.setText(user.username);
                }
            });
            Toast.makeText(getContext(), viewModel.profileMessage.getValue(), Toast.LENGTH_SHORT).show();
            binding.profileEditUsername.setVisibility(View.INVISIBLE);
            binding.profileUsername.setText(View.VISIBLE);
            it.setVisibility(View.INVISIBLE);
            binding.profileEditButton.setVisibility(View.VISIBLE);
        });

        binding.profileDeleteAccount.setOnClickListener(it -> {
            viewModel.deleteAccount(Database.accounts.getValue(), user);
            Toast.makeText(getContext(), viewModel.profileMessage.getValue(), Toast.LENGTH_SHORT).show();
            viewModel.accountDeleted.observe(getViewLifecycleOwner(), newValue -> {
                if (newValue) {
                    LocalData.setLoggedUser("");
                    Intent toSignActivity = new Intent(this.getContext(), SignActivity.class);
                    toSignActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(toSignActivity);
                }
            });
        });

        binding.profileLogOut.setOnClickListener(it -> {
            LocalData.setLoggedUser("");
            Intent toSignActivity = new Intent(this.getContext(), SignActivity.class);
            toSignActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(toSignActivity);
        });

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.profileFragment);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}