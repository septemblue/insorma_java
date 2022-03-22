package com.septemblue.insorma.sign;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private FragmentRegisterBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // register
        binding.registerButton.setOnClickListener(it -> {
            viewModel.register(
                    binding.registerEmailAddress,
                    binding.registerUsername,
                    binding.registerPhoneNumber,
                    binding.registerPassword
            );
            viewModel.registerMessage.observe(getViewLifecycleOwner(), newValue -> {
                Toast.makeText(getActivity(), viewModel.registerMessage.getValue(), Toast.LENGTH_SHORT).show();
            });
        });

        //back to login
        binding.redirectToLoginButton.setOnClickListener(it -> {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
        });


        return view;
    }

}