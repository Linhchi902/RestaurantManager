package com.example.restaurantmanager.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanager.R;
import com.example.restaurantmanager.adapter.ViewPagerAdapter;
import com.example.restaurantmanager.fragment.customer.CategoryFragment;
import com.example.restaurantmanager.fragment.customer.HomeFragment;
import com.example.restaurantmanager.fragment.customer.MenuFragment;
import com.example.restaurantmanager.fragment.customer.OrderFragment;
import com.example.restaurantmanager.fragment.ProductByCategoryFragment;
import com.example.restaurantmanager.fragment.customer.ProductDetailFragment;
import com.example.restaurantmanager.fragment.customer.YourInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainCusActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


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
    private CategoryFragment categoryFragment = new CategoryFragment();
    private YourInfoFragment yourInfoFragment = new YourInfoFragment();
    private ProductDetailFragment fmDetail = new ProductDetailFragment();
    private ProductByCategoryFragment productByCategoryFragment = new ProductByCategoryFragment();
    private MenuFragment fmMenu = new MenuFragment();
    private LinearLayout lnOrder, lnMenu, lndrawer;


    public static boolean isDetail = false;
    public static boolean isProductById = false;
    public static fragPos temp = fragPos.Home;

    public static enum fragPos {
        Home,
        Product
    }

    public static frag tempCa = frag.Category;
    public enum frag{
        Category
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        addFragment();
//        showFragment(homeFragment);
    }

    private void inits() {
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle("Home");

//        lnOrder = findViewById(R.id.ln_order);
//        lnMenu = findViewById(R.id.ln_menu);
//        lnOrder.setOnClickListener(this);
//        lnMenu.setOnClickListener(this);
//        lndrawer = findViewById(R.id.ln_drawer);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), homeFragment,categoryFragment, orderFragment, yourInfoFragment);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);

        bottomNavigationView = findViewById(R.id.bottom_nvg_view);
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

//        toolbar = findViewById(R.id.toolbar);
//        setTitle("Home");
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Home");

//        drawerLayout = findViewById(R.id.drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
//                R.string.drawer_close,R.string.drawer_open);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);

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
                viewPager.setCurrentItem(2);
                showFragment(orderFragment);
                setTitle("Order");
                break;
            case  R.id.navigation_menu:
                viewPager.setCurrentItem(1);
                showFragment(categoryFragment);
                setTitle("Category");
                break;
            case R.id.navigation_your_info:
                viewPager.setCurrentItem(3);
                showFragment(yourInfoFragment);
                setTitle("Your info");
                break;

        }
        return false;
    }

//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        actionBarDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        actionBarDrawerToggle.onConfigurationChanged(newConfig);
//    }

    private void addFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(R.id.panel,homeFragment);
        transaction.add(R.id.panel,orderFragment);
        transaction.add(R.id.panel,categoryFragment);
        transaction.add(R.id.panel,yourInfoFragment);
        transaction.add(R.id.panel, productByCategoryFragment);
        transaction.add(R.id.panel, fmMenu);
        transaction.commit();
    }

    private void showFragment(Fragment fragment){
        hideFrg();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//        transaction.hide(homeFragment);
//        transaction.hide(orderFragment);
//        transaction.hide(categoryFragment);
//        transaction.hide(yourInfoFragment);

        transaction.show(fragment);
        transaction.commit();
    }

    private void hideFrg(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.remove(productByCategoryFragment);
//        transaction.remove(fmMenu);
        transaction.remove(fmDetail);
        transaction.commit();
    }

    public ProductDetailFragment getFmDetail() {
        return fmDetail;
    }

    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public OrderFragment getOrderFragment() {
        return orderFragment;
    }

    public CategoryFragment getCategoryFragment() {
        return categoryFragment;
    }

    public YourInfoFragment getYourInfoFragment() {
        return yourInfoFragment;
    }

    public ProductByCategoryFragment getProductByCategoryFragment() {
        return productByCategoryFragment;
    }

    public MenuFragment getFmMenu() {
        return fmMenu;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isDetail) {
            isDetail = false;
            switch (temp) {
                case Home:
                    showFragment(homeFragment);
                    getActionBar().setTitle("Home");
                    getActionBar().setDisplayHomeAsUpEnabled(false);
                    break;
                case Product:
                    getSupportFragmentManager()
                            .beginTransaction()
//                            .add(R.id.panel,fmWallpaper)
                            .remove(fmDetail)
                            .show(productByCategoryFragment)
                            .commit();
                    isProductById = true;
//                    actionBar.setTitle(productByCategoryFragment.getNameShow());
                    getActionBar().setDisplayHomeAsUpEnabled(true);
                    break;
            }

        }
        else{
            if (isProductById){
                showFragment(categoryFragment);
                isProductById = false;
                getActionBar().setTitle("Category");
                getActionBar().setDisplayHomeAsUpEnabled(false);
            }
            else
                finish();
        }
//        super.onBackPressed();
    }
}
