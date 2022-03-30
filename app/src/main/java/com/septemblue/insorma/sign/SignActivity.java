// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to register user to the database by taking
    all the credentials needed, and user can go back to login to enjoy
    main page

    in this context uses :
    - Navigation Fragment dependency ( tried to keep all login system as 1 scope by 1 activity)
    - Lifecycle ViewModel dependency ( tried to emphasize separation of concern between logic and view)
    - Lifecycle LiveData dependency  ( tried to implement reactive programming )
    - buildFeatures UI layer library View Binding ( simplify findViewById yet provides Null safety
    and type safety)


    for improvement notes :
    - later i think i should use shared view model for register and login
    - in the register view model, i implement shortcut way like a tape
    to solve something not exist in sdk23 and of course it is smelly to bugs
    later on could use solid implementation.

 */
package com.septemblue.insorma.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.septemblue.insorma.R;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }
}