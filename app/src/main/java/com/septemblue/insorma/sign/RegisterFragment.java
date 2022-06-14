// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to register user to the database by taking
    all the credentials needed, and user can go back to login to enjoy
    main page

    for improvement notes :
    - later i think i should use shared view model for register and login
    - in the register view model, i implement shortcut way like a tape
    to solve something not exist in sdk23 and of course it is smelly to bugs
    later on could use solid implementation.

 */
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
import com.septemblue.insorma.storage.DatabaseHelper;
import com.septemblue.insorma.storage.UserHelper;

// please read note above package
public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private FragmentRegisterBinding binding;
    private UserHelper userHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userHelper = new UserHelper(this.getContext());
    }

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
                    binding.registerPassword,
                    userHelper
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

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}