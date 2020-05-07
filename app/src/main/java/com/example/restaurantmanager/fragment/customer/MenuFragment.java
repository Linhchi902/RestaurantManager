package com.example.restaurantmanager.fragment.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.Response.ResponeMenu;
import com.example.restaurantmanager.Response.ResponeProduct;
import com.example.restaurantmanager.activity.ProductActivity;
import com.example.restaurantmanager.adapter.MenuAdapter;
import com.example.restaurantmanager.api.ApiUtils;
import com.example.restaurantmanager.model.Menu;
import com.example.restaurantmanager.model.Product;
import com.example.restaurantmanager.utils.Const;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment implements MenuAdapter.itemMenuOnListener {

    private RecyclerView rcMenu;
    private MenuAdapter adapter;
    private ArrayList<Menu> listMenu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_menu, container, false);
        rcMenu = view.findViewById(R.id.rc_menu);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MenuAdapter(getContext());
        adapter.setListener(this);
        ApiUtils.getData().getAllMenu().enqueue(new Callback<ResponeMenu>() {
            @Override
            public void onResponse(Call<ResponeMenu> call, Response<ResponeMenu> response) {
                listMenu = (ArrayList<Menu>) response.body().getMenu();
                adapter.setListMenu(listMenu);
            }

            @Override
            public void onFailure(Call<ResponeMenu> call, Throwable t) {
                Toast.makeText(getContext(), R.string.message_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void itemMenuClick(int position) {
        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra(Const.MENU_ID,listMenu.get(position).getMenuID());
        startActivity(intent);
    }
}
