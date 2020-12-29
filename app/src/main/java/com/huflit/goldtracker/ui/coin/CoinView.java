package com.huflit.goldtracker.ui.coin;

import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.coin.CoinResponse;

import java.util.List;

public interface CoinView {

    void onLoadCoinSuccess(CoinResponse coinResponse);

    void onLoadCoinFailed();
}
