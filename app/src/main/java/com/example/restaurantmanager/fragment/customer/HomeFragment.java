package com.example.restaurantmanager.fragment.customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.activity.MainCusActivity;
import com.example.restaurantmanager.R;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.adapter.ProductAdapter;
import com.example.restaurantmanager.api.ApiUtils;
import com.example.restaurantmanager.model.Product;
import com.example.restaurantmanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ProductAdapter.ItemProductClick {

    RecyclerView rcProduct;
    private ProductAdapter adapter;
    private List<Product> arr = new ArrayList<>();

//    @Override
//    public int getLayoutID() {
//        return R.layout.fragment_home;
//    }
//
//    @Override
//    public String getTitle() {
//        return null;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home,container,false);
                rcProduct = view.findViewById(R.id.rc_home);
                return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ProductAdapter(getContext());
        adapter.setItemProductClick(this);
        rcProduct.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        rcProduct.setAdapter(adapter);
        ApiUtils.getData().getAllProduct().enqueue(new Callback<ResponeProduct>() {
            @Override
            public void onResponse(Call<ResponeProduct> call, Response<ResponeProduct> response) {
                arr = response.body().getmProducts();
                adapter.setmProducts(arr);
                adapter.notifyDataSetChanged();
                Log.e("TAG", "onResponse: " + response.body().getmProducts().size());
            }

            @Override
            public void onFailure(Call<ResponeProduct> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClicked(int position) {

        MainCusActivity.isDetail = true;
        MainCusActivity.temp = MainCusActivity.fragPos.Home;
        ProductDetailFragment fmDetail  = ((MainCusActivity) getActivity()).getFmDetail();
        Utils.putProduct(fmDetail, position, arr);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .remove(fmDetail)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.panel, fmDetail)
                .hide(this)
                .addToBackStack(null)
                .commit();
    }
}
