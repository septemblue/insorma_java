// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This activity purpose is to register user to the database
    and directing user to main page by validate the user

    in this activity scope uses :
    - Navigation Fragment dependency ( tried to keep all login system as 1 scope by 1 activity)
    - Lifecycle ViewModel dependency ( tried to emphasize separation of concern between logic and view)
    - Lifecycle LiveData dependency  ( tried to implement reactive programming )
    - buildFeatures UI layer library View Binding ( simplify findViewById yet provides Null safety
    and type safety)


    for improvement notes :

 */
package com.septemblue.insorma.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.septemblue.insorma.R;
// please read note above package
public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }
}