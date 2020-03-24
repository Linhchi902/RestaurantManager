package com.example.restaurantmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantmanager.R;
import com.example.restaurantmanager.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Product> mProducts;
    private ItemProductClick itemProductClick;

    public ProductAdapter(Context mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    public List<Product> getmProducts() {
        return mProducts;
    }

    public void setmProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
        notifyDataSetChanged();
    }

    public void setItemProductClick(ItemProductClick itemProductClick) {
        this.itemProductClick = itemProductClick;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product mProduct = mProducts.get(position);
        holder.tvName.setText(mProduct.getPrName());
        holder.tvPrice.setText(mProduct.getPrPrice() + "");
        Glide.with(holder.imgPrd).load(mProduct.getPrImage()).into(holder.imgPrd);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProductClick.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvPrice;
        ImageView imgPrd;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_product);
            tvPrice = itemView.findViewById(R.id.tv_price);
            imgPrd = itemView.findViewById(R.id.img_product);
        }
    }

    public interface ItemProductClick{
        void onItemClicked(int position);
    }
}
