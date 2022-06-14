// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all the furnitures items available in database
    and directing user to the detail if user click the more button on the items card

    in this activity scope uses :
    - Navigation Fragment dependency ( tried to keep all login system as 1 scope by 1 activity)
    - Navigation SafeArgs plugin     ( tried to passing data between fragments safety )
    - Navigation UI dependency       ( tried to navigate from other components to fragment)
    - Lifecycle ViewModel dependency ( tried to emphasize separation of concern between logic and view)
    - Lifecycle LiveData dependency  ( tried to implement reactive programming )
    - buildFeatures UI layer library View Binding ( simplify findViewById yet provides Null safety
    and type safety)
    - buildFeatures UI layer library Data Binding ( tried to bind data to the layout
    for future improvements)
    - Material dependency            ( get material UI and Components )
    - Card view dependency           ( get card view component for UI )

    resource use in this activity scope :
    - 3 material icon : baseline_account, baseline_home, baseline_receipt
    source : https://fonts.google.com/icons?selected=Material+Icons
    - celana jeans
    source : https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.tokopedia.net%2Fimg%2Fcache%2F500-square%2Fproduct-1%2F2019%2F10%2F9%2F6980029%2F6980029_ab29537a-26e1-41ea-96c2-a4d3944748b0&imgrefurl=https%3A%2F%2Fwww.tokopedia.com%2Finklusif%2Flea-original-celana-jeans-lea-606-4311-regular-jeans-pria&tbnid=isvakhBfPUB8RM&vet=12ahUKEwj37qfc3-z2AhVWD7cAHQowCIMQMygIegUIARDrAQ..i&docid=YVypTY3iP0F7sM&w=500&h=500&q=celana%20jean&ved=2ahUKEwj37qfc3-z2AhVWD7cAHQowCIMQMygIegUIARDrAQ
    - sepatu :
    source : https://www.google.com/imgres?imgurl=https%3A%2F%2Fmmc.tirto.id%2Fimage%2Fotf%2F500x0%2F2018%2F06%2F22%2Fnike-air-jordan-1-x-virgil-abloh--nikecom_ratio-16x9.jpg&imgrefurl=https%3A%2F%2Ftirto.id%2Flelang-sepatu-bersejarah-michael-jordan-terjual-rp82-miliar-fxwz&tbnid=ZhWIAdGcyxb1nM&vet=12ahUKEwi32aPz3-z2AhXLyaACHTV-B1wQMygoegUIARC1Ag..i&docid=h2CWdGfdhBXz3M&w=500&h=281&q=sepatu&ved=2ahUKEwi32aPz3-z2AhXLyaACHTV-B1wQMygoegUIARC1Ag
    - baju
    source : https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg.my-best.id%2Fpress_component%2Fimages%2F024b7079bf4ee0f115101e250fd9e0fa.jpeg%3Fixlib%3Drails-4.2.0%26q%3D70%26lossless%3D0%26w%3D690%26fit%3Dmax&imgrefurl=https%3A%2F%2Fmy-best.id%2F137881&tbnid=J9o9y-1xc_6FnM&vet=12ahUKEwj2172E4Oz2AhXVndgFHT_1BtoQMyg7egQIARBv..i&docid=UsuThd9zA8Ic_M&w=391&h=396&q=baju&ved=2ahUKEwj2172E4Oz2AhXVndgFHT_1BtoQMyg7egQIARBv

    for improvement notes :
    -

 */
package com.septemblue.insorma.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.ActivityMainBinding;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Cache;

import java.util.Objects;
// please read note above package
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding + view model
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // set material toolbar
        // give appbar configuration
        // and give navigation UI
        setSupportActionBar(binding.mainToolbar);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration.Builder builder = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.transactionHistoryFragment);
        AppBarConfiguration appBarConfiguration = builder.build();
        NavigationUI.setupWithNavController(binding.mainToolbar, navController, appBarConfiguration);

        // material bottom navigation
        binding.mainBottomNav.setOnItemSelectedListener(it -> {
            if (it.getItemId() == R.id.homeFragment) {
                Navigation.findNavController(this, R.id.main_nav_host_fragment).navigate(R.id.homeFragment);
                return true;
            } else if (it.getItemId() == R.id.transactionHistoryFragment) {
                Navigation.findNavController(this, R.id.main_nav_host_fragment).navigate(R.id.transactionHistoryFragment);
                return true;
            }else return false;
        });
    }

    // display username on the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_toolbar, menu);
        MenuItem item = menu.findItem(R.id.toolbar_username);
        item.setTitle(Objects.requireNonNull(Cache.getLoggedUser().getValue()).username);
        return super.onCreateOptionsMenu(menu);
    }

    // navigate to fragment which item id has
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.main_nav_host_fragment)) || super.onOptionsItemSelected(item);
    }
}