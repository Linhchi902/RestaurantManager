package com.example.restaurantmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.model.Account;
import com.example.restaurantmanager.api.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private TextView tvSingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edt_user_name);
        edtPassword = findViewById(R.id.edt_password);
        tvSingin = findViewById(R.id.tv_sing_in);
        tvSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (userName.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"Data empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    ApiUtils.getData().login(userName,password).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                             if (response.code() == 200){
                                 if(response.body().getAcGrant() == 1){
                                     Toast.makeText(LoginActivity.this,"Admin Login success",Toast.LENGTH_SHORT).show();
                                 }
                                 else if (response.body().getAcGrant() == 2){
                                     Toast.makeText(LoginActivity.this,"Employee Login success",Toast.LENGTH_SHORT).show();
                                 }
                                 else{
                                     Toast.makeText(LoginActivity.this,"Customer Login success",Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(LoginActivity.this, MainCusActivity.class);
                                     intent.putExtra("name", response.body().getAcName());
                                     intent.putExtra("grant",response.body().getAcGrant());
                                     startActivity(intent);
                                 }


                            }
                            else {
                                Toast.makeText(LoginActivity.this,"User name or password not right!",Toast.LENGTH_SHORT).show();
                                edtUserName.setText("");
                                edtUserName.requestFocus();
                                edtPassword.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Log.e("aaaa", "onFailure: " + t.getMessage() );
                            Toast.makeText(LoginActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
