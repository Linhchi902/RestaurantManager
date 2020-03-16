package com.example.restaurantmanager.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.restaurantmanager.R;

public class Utils {

    public static void loadImage(Context mContext, String url, ImageView imageView){
        if (url == null || url.equalsIgnoreCase("")){
            Toast.makeText(mContext, "Image empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Glide.with(imageView).load(url).into(imageView);
        }
    }
}
