package com.example.restaurantmanager.api;

import com.example.restaurantmanager.api.Api;
import com.example.restaurantmanager.api.ApiBuilder;

public class ApiUtils {

    public static final String baseURL = "http://192.168.1.82/restaurant_mng/";

    public static Api getData() {
        return ApiBuilder.getClient(baseURL).create(Api.class);
    }
}
