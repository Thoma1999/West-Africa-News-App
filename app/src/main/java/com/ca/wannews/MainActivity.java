package com.ca.wannews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;




public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager2 viewPager;
    private DrawerLayout drawer;

    private static final int NUM_PAGES = 5;
    private FragmentStateAdapter pagerAdapter;

    private ArrayList<String> tabNames = new ArrayList<>();

    private int[] tabIcons = {
            R.drawable.ic_sports,
            R.drawable.ic_business,
            R.drawable.ic_technology,
            R.drawable.ic_entertainment,
            R.drawable.ic_health
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setTabs();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        tabNames.add("Sports");
        tabNames.add("Business");
        tabNames.add("Technology");
        tabNames.add("Entertainment");
        tabNames.add("Economics");

        //Navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        setTabs();
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_settings:
                Intent intent = new Intent(this,SettingsActivity.class);
                this.startActivity(intent);
                break;
            case R.id.nav_about:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setTabs() {
        TabLayout tabLayout = findViewById(R.id.tablayout);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            new TabLayoutMediator(tabLayout, viewPager,
                    (tab, position) -> tab.setText(tabNames.get(position))
            ).attach();
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            new TabLayoutMediator(tabLayout, viewPager,
                    (tab, position) -> tab.setIcon(tabIcons[position])
            ).attach();
        }
    }





    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {


        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new SportsFragment();
                case 1:
                    return new BusinessFragment();
                case 2:
                    return new TechnologyFragment();
                case 3:
                    return new EntertainmentFragment();
                case 4:
                    return new HealthFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}
