package com.example.restaurantmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.restaurantmanager.adapter.ViewPagerAdapter;
import com.example.restaurantmanager.fragment.FavoriteFragment;
import com.example.restaurantmanager.fragment.HomeFragment;
import com.example.restaurantmanager.fragment.OrderFragment;
import com.example.restaurantmanager.fragment.ProductDetailFragment;
import com.example.restaurantmanager.fragment.YourInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;
    private MenuItem item;
    private Toolbar toolbar;
    private DrawerLayout  drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView textView;

    private HomeFragment homeFragment = new HomeFragment();
    private OrderFragment orderFragment =  new OrderFragment();
    private FavoriteFragment favoriteFragment = new FavoriteFragment();
    private YourInfoFragment yourInfoFragment = new YourInfoFragment();
    private ProductDetailFragment fmDetail = new ProductDetailFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
//        addFragment();
//        showFragment(homeFragment);
    }

    private void inits() {
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle("Home");

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), homeFragment, orderFragment, favoriteFragment, yourInfoFragment);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (item != null){
                    item.setChecked(false);
                }
                else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOffscreenPageLimit(4);

        toolbar = findViewById(R.id.toolbar);
        setTitle("Home");
        setSupportActionBar(toolbar);
//        toolbar.setTitle("Home");

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_close,R.string.drawer_open);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

//        textView = findViewById(R.id.toolbar_title);
//        textView.setText("Home");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                viewPager.setCurrentItem(0);
                showFragment(homeFragment);
//                textView.setText("Home");
                setTitle("Home");
                break;
            case R.id.navigation_order:
                viewPager.setCurrentItem(1);
                showFragment(orderFragment);
                setTitle("Order");
                break;
            case  R.id.navigation_favorite:
                viewPager.setCurrentItem(2);
                showFragment(favoriteFragment);
                setTitle("Favorite");
                break;
            case R.id.navigation_your_info:
                viewPager.setCurrentItem(3);
                showFragment(yourInfoFragment);
                setTitle("Your info");
                break;

        }
        return false;
    }

    private void addFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(R.id.panel,homeFragment);
        transaction.add(R.id.panel,orderFragment);
        transaction.add(R.id.panel,favoriteFragment);
        transaction.add(R.id.panel,yourInfoFragment);
        transaction.commit();
    }

    private void showFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.hide(homeFragment);
        transaction.hide(orderFragment);
        transaction.hide(favoriteFragment);
        transaction.hide(yourInfoFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    public ProductDetailFragment getFmDetail() {
        return fmDetail;
    }
}
