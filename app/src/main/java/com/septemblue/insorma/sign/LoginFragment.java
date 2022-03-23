package com.septemblue.insorma.sign;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.septemblue.insorma.main.MainActivity;
import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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