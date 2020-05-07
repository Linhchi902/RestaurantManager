package com.example.restaurantmanager.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.api.ApiUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtAddress;
    private EditText edtBirth;
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnRegister;
    private ImageView imgBirth;
    private TextView tvLogin;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_name_ct);
        edtAddress = findViewById(R.id.edt_address_ct);
        edtBirth = findViewById(R.id.edt_birth_ct);
        edtPhone = findViewById(R.id.edt_phone_ct);
        edtUserName = findViewById(R.id.edt_user_name_ct);
        edtPassword = findViewById(R.id.edt_password_ct);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password_ct);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        imgBirth = findViewById(R.id.img_birth);
        imgBirth.setOnClickListener(this);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_birth){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            edtBirth.setText( year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v.getId() == R.id.btn_register){
            String name = edtName.getText().toString();
            String address = edtAddress.getText().toString();
            String phone = edtPhone.getText().toString();
            String birthDate = edtBirth.getText().toString();
            String userName = edtUserName.getText().toString();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();



            if (name.equals("")){
                showDialog("Ok", R.string.message_name_empty );
            }
            else if (phone.equals("")){
                showDialog("Ok",R.string.message_phone_empty);
            }
            else if (phone.trim().length() <10 || phone.trim().length() >10){
                showDialog("Ok",R.string.message_phone_false);
            }else if (address.equals("")){
                showDialog("Ok",R.string.message_address_empty);
            }
            else if (birthDate.equals("")){
                showDialog("Ok",R.string.message_birth_empty);
            }
            else if (userName.equals("")){
                showDialog("Ok",R.string.message_user_name_empty);
            }
            else if (password.equals("")){
                showDialog("Ok",R.string.message_password_empty);
            }
            else if (confirmPassword.equals("")){
                showDialog("Ok",R.string.message_password_confirm_empty);
            }
            else if (password.length() < 8){
                showDialog("Ok",R.string.message_password_short);
            }
            else if (confirmPassword.trim().equalsIgnoreCase(password.trim()) == false){
                showDialog("Ok",R.string.message_false_password);
            }
            else {
                    ApiUtils.getData().register(name,phone,birthDate, address, userName, password).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 200){
                                Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            showDialog("Ok",R.string.message_error);
                            Log.e("TAG", "onFailure: " + t.getMessage() );
                        }
                    });

            }
        }

        if (v.getId() == R.id.tv_login){
            finish();
        }

    }

    @SuppressLint("ResourceType")
    public void showDialog(String title, @IdRes int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notification);
        builder.setMessage(id);
        builder.setCancelable(true);
        builder.setPositiveButton(title, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }
}
