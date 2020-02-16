package com.example.restaurantmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanager.api.ApiBuilder;
import com.example.restaurantmanager.model.Account;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edt_user_name);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);


        try {
            mSocket = IO.socket("http://192.168.1.119:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (userName.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this,"Data empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    ApiBuilder.getApi().login(userName,password).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                             if (response.code() == 200){
                                 if(response.body().getAcGrant() == 1){
                                     Toast.makeText(MainActivity.this,"Admin Login success",Toast.LENGTH_SHORT).show();
                                 }
                                 else if (response.body().getAcGrant() == 2){
                                     Toast.makeText(MainActivity.this,"Employee Login success",Toast.LENGTH_SHORT).show();
                                 }
                                 else{
                                     Toast.makeText(MainActivity.this,"Customer Login success",Toast.LENGTH_SHORT).show();
                                 }

                            }
                            else {
                                Toast.makeText(MainActivity.this,"False",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Log.e("aaaa", "onFailure: " + t.getMessage() );
                            Toast.makeText(MainActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
