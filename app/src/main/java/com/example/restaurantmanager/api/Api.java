package com.example.restaurantmanager.api;

import com.example.restaurantmanager.model.Account;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("login.php")
    @FormUrlEncoded
    Call<Account> login(@Field("acName") String name,
                        @Field("acPassword") String password);
}
