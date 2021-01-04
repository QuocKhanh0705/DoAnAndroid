package com.huflit.goldtracker.ui.coin;

import com.huflit.goldtracker.data.model.coin.Coin;

import java.util.List;

public interface CoinView {

    void onLoadCoinSuccess(List<Coin> coins);

    void onLoadCoinFailed();
}
