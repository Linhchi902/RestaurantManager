package com.example.restaurantmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.activity.ProductActivity;
import com.example.restaurantmanager.model.Menu;
import com.example.restaurantmanager.utils.Const;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<Menu> listMenu = new ArrayList<>();
    private itemMenuOnListener listener;

    public MenuAdapter(Context mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    public ArrayList<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
        notifyDataSetChanged();
    }

    public itemMenuOnListener getListener() {
        return listener;
    }

    public void setListener(itemMenuOnListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_menu,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = listMenu.get(position);
        holder.tvName.setText(menu.getMenuName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ProductActivity.class);
//                intent.putExtra(Const.MENU_ID,menu.getMenuID());
//                mContext.startActivity(intent);
                listener.itemMenuClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu==null ? 0 : listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_menu);
        }
    }

    public interface itemMenuOnListener{
        void itemMenuClick(int position);
    }
}
