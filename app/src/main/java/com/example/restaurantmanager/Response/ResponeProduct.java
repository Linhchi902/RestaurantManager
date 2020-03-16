package com.example.restaurantmanager.Response;

import com.example.restaurantmanager.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponeProduct {

    @SerializedName("product")
    private List<Product> mProducts;

    public List<Product> getmProducts() {
        return mProducts;
    }

    public void setmProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
    }
}
