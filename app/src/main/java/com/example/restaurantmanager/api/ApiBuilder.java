package com.example.restaurantmanager.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private static Api api;
    public static final String baseURL = "http://192.168.1.119/restaurant_mng/";

    public static Api getApi() {
        if (api == null){
            api = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api.class);
        }
        return api;
    }
}
