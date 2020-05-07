package com.example.restaurantmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.adapter.ProductAdapter;
import com.example.restaurantmanager.api.ApiUtils;
import com.example.restaurantmanager.utils.Const;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    @BindView(R.id.rcProduct)
    RecyclerView rcProduct;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent i = getIntent();
        int id = i.getIntExtra(Const.MENU_ID, 5);
        adapter = new ProductAdapter(this);
        ApiUtils.getData().getProductbyIdMenu(id).enqueue(new Callback<ResponeProduct>() {
            @Override
            public void onResponse(Call<ResponeProduct> call, Response<ResponeProduct> response) {
                adapter.setmProducts(response.body().getmProducts());
                Log.e("TAG", "onResponse: "+ response.body().getmProducts().size() );
            }

            @Override
            public void onFailure(Call<ResponeProduct> call, Throwable t) {
                Log.e("TAG", "onResponse: "+ t.getMessage());
            }
        });
        if (adapter.getmProducts()!= null){
            rcProduct.setAdapter(adapter);
        }
        else {
            Toast.makeText(this,"Adapter null",Toast.LENGTH_SHORT).show();
        }
    }
}
