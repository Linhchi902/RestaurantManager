package com.example.restaurantmanager.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.activity.admin.AddAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountManagerAcitivity extends AppCompatActivity {

    private RecyclerView rcAccount;
    private FloatingActionButton flBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);

        rcAccount = findViewById(R.id.rc_account);
        flBtnAdd = findViewById(R.id.float_btn_add_account);
        flBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAccount addAccount = new AddAccount();
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .add(R.id.frame, addAccount)
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .show(addAccount)
                        .commit();
            }
        });
    }
}
