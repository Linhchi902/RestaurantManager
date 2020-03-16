package com.example.restaurantmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.utils.Const;

public class ProductDetailFragment extends Fragment {

    private TextView tvName;
    private TextView tvPrice;
    private TextView tvTime;
    private TextView tvAmount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_detail_product,container, false);
        getActivity().setTitle("Detail");
        tvName = getActivity().findViewById(R.id.tv_name_product_detail);
        tvPrice = getActivity().findViewById(R.id.tv_price_detail);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName.setText(getArguments().getString(Const.PR_NAME));
        tvPrice.setText(getArguments().getString(Const.PR_PRICE));
    }
}
