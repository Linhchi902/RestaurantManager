package com.example.restaurantmanager.utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.restaurantmanager.fragment.customer.ProductDetailFragment;
import com.example.restaurantmanager.model.Product;

import java.util.List;

public class Utils {

    public static void loadImage(Context mContext, String url, ImageView imageView){
        if (url == null || url.equalsIgnoreCase("")){
            Toast.makeText(mContext, "Image empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Glide.with(imageView).load(url).into(imageView);
        }
    }

    public static void putProduct(ProductDetailFragment fmDetail, int position, List<Product> arr){
        Bundle b =  new Bundle();
        b.putInt(Const.PR_ID,arr.get(position).getPrID());
        b.putString(Const.PR_NAME, arr.get(position).getPrName());
        b.putInt(Const.PR_PRICE, arr.get(position).getPrPrice());
        b.putInt(Const.PR_AMOUNT, arr.get(position).getPrAmount());
        b.putInt(Const.PR_TIME, arr.get(position).getPrTime());
        b.putString(Const.PR_UNIT, arr.get(position).getPrUnit());
        b.putString(Const.PR_IMAGE, arr.get(position).getPrImage());
        b.putInt(Const.PR_MENUID, arr.get(position).getMenuID());
        fmDetail.setArguments(b);
    }
}
