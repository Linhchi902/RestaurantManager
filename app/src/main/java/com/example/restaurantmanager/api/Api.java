package com.example.restaurantmanager.api;

import com.example.restaurantmanager.Response.ResponeMenu;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.model.Account;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("register.php")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("ctName") String ctName,
                                @Field("ctPhone") String ctPhone,
                                @Field("ctBirth") String ctBirth,
                                @Field("ctAddress") String ctAddress,
                                @Field("acName") String acName,
                                @Field("acPassword") String acPassword);

    @POST("login.php")
    @FormUrlEncoded
    Call<Account> login(@Field("acName") String name,
                        @Field("acPassword") String password);

    @GET("getproduct.php")
    Call<ResponeProduct> getAllProduct();

    @GET("getmenu.php")
    Call<ResponeMenu> getAllMenu();

    @POST("getproductbyidmenu.php")
    @FormUrlEncoded
    Call<ResponeProduct> getProductbyIdMenu(@Field("menuID") int menuID);
}
