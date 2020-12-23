package com.huflit.goldtracker.ui.coin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.List;

public class CoinFragment extends BaseFragment implements CoinView {

    private CoinPresenter coinPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_bitcoin;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        coinPresenter = new CoinPresenter(this);
        coinPresenter.getCoin();
    }

    @Override
    public void onLoadCoinSuccess(List<Coin> coins) {
        Log.e("Coin", "size: " + coins.size());
    }

    @Override
    public void onLoadCoinFailed() {
        Log.e("Coin", "onLoadCoinFailed");
    }
}
