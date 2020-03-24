package com.example.restaurantmanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.restaurantmanager.R;
import com.example.restaurantmanager.utils.Const;

public class ProductDetailFragment extends Fragment {

    private TextView tvName;
    private TextView tvPrice;
    private TextView tvTime;
    private TextView tvAmount;
    private ImageView imgProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_detail_product,container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Detail");
        tvName = getActivity().findViewById(R.id.tv_name_product_detail);
        tvPrice = getActivity().findViewById(R.id.tv_price_detail);
        tvTime = getActivity().findViewById(R.id.tv_time_detail);
        tvAmount = getActivity().findViewById(R.id.tv_amount_detail);
        imgProduct = getActivity().findViewById(R.id.img_product_detail);
        Bundle b = getArguments();
        if (b != null){
            Log.e("TAG", "onActivityCreated: " + b.getString(Const.PR_NAME) );
            Glide.with(imgProduct).load(b.getString(Const.PR_IMAGE)).into(imgProduct);
            tvName.setText(b.getString(Const.PR_NAME));
            tvPrice.setText("Giá: " + b.getInt(Const.PR_PRICE ) + " đồng.");
            tvTime.setText("Thời gian thực hiện: " + b.getInt(Const.PR_TIME) + " phút");
            tvAmount.setText("Số lượng: " + b.getInt(Const.PR_AMOUNT) + " " + b.getString(Const.PR_UNIT));
        }
        else {
            Log.e("TAG", "onActivityCreated: bundle null" );
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
