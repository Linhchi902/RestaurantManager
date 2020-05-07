package com.example.restaurantmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.activity.MainCusActivity;
import com.example.restaurantmanager.R;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.adapter.ProductAdapter;
import com.example.restaurantmanager.api.ApiUtils;
import com.example.restaurantmanager.fragment.customer.ProductDetailFragment;
import com.example.restaurantmanager.model.Product;
import com.example.restaurantmanager.utils.Const;
import com.example.restaurantmanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductByCategoryFragment extends Fragment implements ProductAdapter.ItemProductClick {

    @BindView(R.id.rcProduct)
    RecyclerView rcProduct;
    private ProductAdapter adapter;
    private List<Product> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.fragment_product_by_category,container, false);
        rcProduct = view.findViewById(R.id.rc_product_by_id);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ProductAdapter(getContext());
        adapter.setItemProductClick(this);
        Bundle b = getArguments();
        if (b != null){
            ApiUtils.getData().getProductbyIdMenu(b.getInt(Const.MENU_ID)).enqueue(new Callback<ResponeProduct>() {
                @Override
                public void onResponse(Call<ResponeProduct> call, Response<ResponeProduct> response) {
                    mList = response.body().getmProducts();
                    adapter.setmProducts(mList);
                }

                @Override
                public void onFailure(Call<ResponeProduct> call, Throwable t) {

                }
            });
            rcProduct.setAdapter(adapter);
        }
        else {
            Toast.makeText(getContext(), R.string.message_error, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClicked(int position) {
        ProductDetailFragment fmDetailFragment = ((MainCusActivity) getActivity()).getFmDetail();
        Utils.putProduct(fmDetailFragment, position, mList );
        MainCusActivity.isDetail = true;
        MainCusActivity.temp = MainCusActivity.fragPos.Product;
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .remove(fmDetailFragment)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.panel, fmDetailFragment)
                .hide(this)
                .addToBackStack(null)
                .commit();
    }
}
