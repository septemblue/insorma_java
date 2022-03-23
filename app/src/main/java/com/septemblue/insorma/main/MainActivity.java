package com.septemblue.insorma.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.ActivityMainBinding;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.LocalData;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String user = LocalData.getLoggedUser().getValue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.mainToolbar);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration.Builder builder = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.transactionHistoryFragment);
        AppBarConfiguration appBarConfiguration = builder.build();
        NavigationUI.setupWithNavController(binding.mainToolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.mainBottomNav, navController);
    }

    // todo on option menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_toolbar, menu);
        MenuItem item = menu.findItem(R.id.toolbar_username);
        item.setTitle(Objects.requireNonNull(Database.accounts.getValue().get(user)).username);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.main_nav_host_fragment)) || super.onOptionsItemSelected(item);
    }
}