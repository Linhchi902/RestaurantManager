package com.example.restaurantmanager.fragment.customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.restaurantmanager.activity.MainCusActivity;
import com.example.restaurantmanager.R;
import com.example.restaurantmanager.model.Product;
import com.example.restaurantmanager.utils.Const;

public class ProductDetailFragment extends Fragment implements View.OnClickListener {

    private TextView tvName;
    private TextView tvPrice;
    private TextView tvTime;
    private TextView tvAmount;
    private ImageView imgProduct;
    private Button btnChoose;
    private Product product;
    private int id;
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

        btnChoose = getActivity().findViewById(R.id.btn_order);
        btnChoose.setOnClickListener(this);
        Bundle b = getArguments();
        if (b != null){
            Log.e("TAG", "onActivityCreated: " + b.getString(Const.PR_NAME) );
            Glide.with(imgProduct).load(b.getString(Const.PR_IMAGE)).into(imgProduct);
            tvName.setText(b.getString(Const.PR_NAME));
            tvPrice.setText("Giá: " + b.getInt(Const.PR_PRICE ) + " đồng.");
            tvTime.setText("Thời gian thực hiện: " + b.getInt(Const.PR_TIME) + " phút");
            tvAmount.setText("Số lượng: " + b.getInt(Const.PR_AMOUNT) + " " + b.getString(Const.PR_UNIT));
            id = b.getInt(Const.PR_ID);
            product = new Product(id, b.getString(Const.PR_NAME),b.getInt(Const.PR_PRICE),
                    b.getInt(Const.PR_AMOUNT),b.getString(Const.PR_UNIT), b.getInt(Const.PR_TIME),
                    b.getString(Const.PR_IMAGE), b.getInt(Const.PR_MENUID));
        }
        else {
            Log.e("TAG", "onActivityCreated: bundle null" );
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        Bundle b = new Bundle();
        b.putInt(Const.PR_ID,id);
        b.putString(Const.PR_NAME,product.getPrName() );
        b.putInt(Const.PR_PRICE, product.getPrPrice());
        b.putInt(Const.PR_AMOUNT, product.getPrAmount());
        b.putInt(Const.PR_TIME, product.getPrTime());
        b.putString(Const.PR_UNIT, product.getPrUnit());
        b.putString(Const.PR_IMAGE, product.getPrImage());
        b.putInt(Const.PR_MENUID, product.getMenuID());
        OrderFragment orderFragment = ((MainCusActivity) getActivity()).getOrderFragment();
        orderFragment.setArguments(b);

    }
}
