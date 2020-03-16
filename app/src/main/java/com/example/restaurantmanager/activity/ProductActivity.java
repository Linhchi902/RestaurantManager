package com.example.restaurantmanager.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.adapter.ProductAdapter;
import com.example.restaurantmanager.api.AppUtils;

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
        adapter = new ProductAdapter(this);
        AppUtils.getData().getAllProduct().enqueue(new Callback<ResponeProduct>() {
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
