package com.huflit.goldtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huflit.goldtracker.ui.coin.CoinFragment;
import com.huflit.goldtracker.ui.exchange.ExchangeFragment;
import com.huflit.goldtracker.ui.gold.GoldFragment;
import com.huflit.goldtracker.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new GoldFragment());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_gold:
                fragment = new GoldFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_exchange:
                fragment = new ExchangeFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_bitcoin:
                fragment = new CoinFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}