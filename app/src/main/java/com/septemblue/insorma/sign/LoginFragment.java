// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is authenticate registered user
    by giving email address and password
    if the user doesn't have an account they can go to
    register fragment to register

    for improvement notes :
    - later i think i should use shared view model for register and login
    - here i handle the backpressed hardware button, so the user can't go back to main page
    after signing out, but this is just a shortcut way to solve like a tape, i should make
    main page always validate the user logged cache

 */
package com.septemblue.insorma.sign;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.septemblue.insorma.main.MainActivity;
import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentLoginBinding;

// please read note above package
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.startDatabase(this.getContext());

        // Handle the back press hardware button, so the user can't go back to main page
        // after signing out
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
        // View binding + view model
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //login
        binding.loginButton.setOnClickListener(it -> {
            viewModel.login(binding.loginEmailAddress, binding.loginPassword);
            // Give login notification
            viewModel.logged.observe(getViewLifecycleOwner(), newValue -> Toast.makeText(getActivity(), viewModel.loginMessage.getValue(), Toast.LENGTH_SHORT).show());
        });

        // navigate if succeed
        viewModel.logged.observe(getViewLifecycleOwner(), newValue -> {
            if (newValue) {
                Intent toMainActivity = new Intent(this.getContext(), MainActivity.class);
                toMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toMainActivity);
            }
        });

        //register
        binding.loginRegisterButton.setOnClickListener(it ->  {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}