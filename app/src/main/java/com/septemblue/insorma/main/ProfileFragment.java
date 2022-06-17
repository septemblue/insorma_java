// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all the profile data, edit the profile
    or delete the account. It also used for signing out from the main page.

    for improvement notes :
    - here i still used non view logic and should be implemented in view model
    i will update it when the project permitted to access database
    - i haven't completely implement reactive programmng

 */
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
import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.sign.SignActivity;
import com.septemblue.insorma.storage.DatabaseHelper;
import com.septemblue.insorma.storage.UserHelper;
import com.septemblue.insorma.storage.UserModel;

// please read note above package
public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;
    private UserHelper userHelper;
    // has option menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userHelper = new UserHelper(this.getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // binding + view model
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        // get the account from cache
        userHelper.updateUserList();
        UserModel user = Cache.getLoggedUser().getValue();

        // give layout profile data
        binding.profileUsername.setText(user.username);
        binding.profileEmailAddress.setText(user.emailAddress);
        binding.profilePhoneNumber.setText(user.phoneNumber);

        // button to edit username
        binding.profileEditButton.setOnClickListener(it -> {
            binding.profileUsername.setVisibility(View.INVISIBLE);
            binding.profileEditUsername.setVisibility(View.VISIBLE);
            it.setVisibility(View.INVISIBLE);
            binding.profileSaveButton.setVisibility(View.VISIBLE);
        });

        // save the edit value and give notification
        binding.profileSaveButton.setOnClickListener(it -> {
            viewModel.editUsername(binding.profileEditUsername, Database.accounts.getValue(), userHelper);
            viewModel.usernameChanged.observe(getViewLifecycleOwner(), newValue -> {
                if (newValue) {
                    String newUsername = Cache.getLoggedUser().getValue().username;
                    binding.profileUsername.setText(newUsername);
                }
            });
            Toast.makeText(getContext(), viewModel.profileMessage.getValue(), Toast.LENGTH_SHORT).show();
            binding.profileEditUsername.setVisibility(View.INVISIBLE);
            binding.profileUsername.setVisibility(View.VISIBLE);
            it.setVisibility(View.INVISIBLE);
            binding.profileEditButton.setVisibility(View.VISIBLE);
        });

        // button to delete account and redirect to sign activity
        binding.profileDeleteAccount.setOnClickListener(it -> {
            viewModel.deleteAccount(userHelper);
            Toast.makeText(getContext(), viewModel.profileMessage.getValue(), Toast.LENGTH_SHORT).show();
            viewModel.accountDeleted.observe(getViewLifecycleOwner(), newValue -> {
                if (newValue) {
                    Cache.setLoggedUser("", userHelper.users);
                    Intent toSignActivity = new Intent(this.getContext(), SignActivity.class);
                    toSignActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(toSignActivity);
                }
            });
        });

        // button for clear logged cache and redirect to sign activity
        binding.profileLogOut.setOnClickListener(it -> {
            Cache.setLoggedUser("", userHelper.users);
            Intent toSignActivity = new Intent(this.getContext(), SignActivity.class);
            toSignActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(toSignActivity);
        });

        return view;
    }

    // hide profile icon
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.profileFragment);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}