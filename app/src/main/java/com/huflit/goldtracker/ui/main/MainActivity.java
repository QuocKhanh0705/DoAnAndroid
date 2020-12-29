package com.huflit.goldtracker.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huflit.goldtracker.R;
import com.huflit.goldtracker.ui.coin.CoinFragment;
import com.huflit.goldtracker.ui.exchange.ExchangeFragment;
import com.huflit.goldtracker.ui.gold.GoldFragment;
import com.huflit.goldtracker.ui.settings.SettingsFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ProgressBar progress;
    private Calendar goldCalendar = Calendar.getInstance();
    private Calendar exchangeCalendar = Calendar.getInstance();
    private Calendar coinCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = findViewById(R.id.progress);

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

    public void showProgress() {
        if (progress.getVisibility() != View.VISIBLE) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        if (progress.getVisibility() != View.GONE) {
            progress.setVisibility(View.GONE);
        }
    }

    public void setGoldCalendar(Calendar goldCalendar) {
        this.goldCalendar = goldCalendar;
    }

    public Calendar getGoldCalendar() {
        return goldCalendar;
    }

    public Calendar getExchangeCalendar() {
        return exchangeCalendar;
    }

    public void setExchangeCalendar(Calendar exchangeCalendar) {
        this.exchangeCalendar = exchangeCalendar;
    }
    public  void setCoinCalendar(Calendar coinCalendar) {this.coinCalendar = coinCalendar;}
    public  Calendar getCoinCalenda()
    {return coinCalendar; }
}