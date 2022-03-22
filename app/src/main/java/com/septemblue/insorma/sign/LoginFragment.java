package com.septemblue.insorma.sign;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.septemblue.insorma.databinding.FragmentLoginBinding;
import com.septemblue.insorma.local.Database;

import kotlin.Lazy;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // View binding + view model
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //login
        binding.loginButton.setOnClickListener(it -> {
            String user = viewModel.login(binding.loginEmailAddress, binding.loginPassword);
        });

        return view;
    }
}