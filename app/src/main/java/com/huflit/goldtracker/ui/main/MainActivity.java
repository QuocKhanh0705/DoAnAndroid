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
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.BaseGold;
import com.huflit.goldtracker.data.model.gold.BaseExchange;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.coin.CoinFragment;
import com.huflit.goldtracker.ui.exchange.ExchangeFragment;
import com.huflit.goldtracker.ui.gold.GoldFragment;
import com.huflit.goldtracker.ui.settings.SettingsFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainView {

    private ProgressBar progress;
    private MainPresenter mainPresenter;

    private List<BaseGold> goldList;
    private List<BaseExchange> rateList;
    private List<Coin> coinList;

    private boolean isGoldLoaded = false;
    private boolean isCoinLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = findViewById(R.id.progress);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        mainPresenter = new MainPresenter(this);
        showProgress();
        mainPresenter.getData();
        mainPresenter.getCoin();
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

    @Override
    public void onLoadDataSuccess(TyGiaResponse tyGiaResponse) {
        isGoldLoaded = true;
        progressState();
        goldList = tyGiaResponse.getGolds();
        rateList = tyGiaResponse.getRates();
        loadFragment(new GoldFragment());
    }

    @Override
    public void onLoadDataFailed() {
        isGoldLoaded = true;
        progressState();
    }

    @Override
    public void onLoadCoinSuccess(List<Coin> coins) {
        isCoinLoaded = true;
        progressState();
        coinList = coins;
    }

    @Override
    public void onLoadCoinFailed() {
        isCoinLoaded = true;
        progressState();
    }

    private void progressState() {
        if (isGoldLoaded && isCoinLoaded) {
            hideProgress();
        }
    }

    private void showProgress() {
        if (progress.getVisibility() != View.VISIBLE) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgress() {
        if (progress.getVisibility() != View.GONE) {
            progress.setVisibility(View.GONE);
        }
    }

    public List<BaseGold> getGoldList() {
        return goldList;
    }

    public List<BaseExchange> getRateList() {
        return rateList;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }
}