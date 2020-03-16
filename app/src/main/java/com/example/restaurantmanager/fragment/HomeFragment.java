package com.example.restaurantmanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.MainActivity;
import com.example.restaurantmanager.R;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.adapter.ProductAdapter;
import com.example.restaurantmanager.api.AppUtils;
import com.example.restaurantmanager.base.BaseFragment;
import com.example.restaurantmanager.model.Product;
import com.example.restaurantmanager.utils.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppUtils.getData().getAllProduct().enqueue(new Callback<ResponeProduct>() {
            @Override
            public void onResponse(Call<ResponeProduct> call, Response<ResponeProduct> response) {
                arr = response.body().getmProducts();
                adapter.setmProducts(arr);
                adapter.notifyDataSetChanged();
                Log.e("TAG", "onResponse: " + response.body().getmProducts().size());
            }

            @Override
            public void onFailure(Call<ResponeProduct> call, Throwable t) {
                Log.e("TAG", "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Bundle b =  new Bundle();
        b.putInt(Const.PR_ID,arr.get(position).getPrID());
        b.putString(Const.PR_NAME, arr.get(position).getPrName());
        b.putString(Const.PR_PRICE, arr.get(position).getPrPrice());
        b.putString(Const.PR_AMOUNT, arr.get(position).getPrAmount());
        b.putString(Const.PR_TIME, arr.get(position).getPrTime());
        b.putString(Const.PR_UNIT, arr.get(position).getPrUnit());
        b.putString(Const.PR_IMAGE, arr.get(position).getPrImage());
        b.putString(Const.PR_MENUID, arr.get(position).getMenuID());
        setArguments(b);
        Fragment fmDetail  = ((MainActivity) getActivity()).getFmDetail();
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
