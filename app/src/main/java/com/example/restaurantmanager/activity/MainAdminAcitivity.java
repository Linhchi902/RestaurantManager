package com.example.restaurantmanager.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanager.R;

public class MainAdminAcitivity extends AppCompatActivity {

    private Button btnAccountMng, btnEmployeeMng, btnProductMng, btnBillMng, btnCategoryMng;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAccountMng = findViewById(R.id.btn_account_manager);
        btnBillMng = findViewById(R.id.btn_bill_manager);
        btnCategoryMng = findViewById(R.id.btn_category_manager);
        btnEmployeeMng = findViewById(R.id.btn_emp_manager);
        btnProductMng = findViewById(R.id.btn_product_manager);
        
    }
}
