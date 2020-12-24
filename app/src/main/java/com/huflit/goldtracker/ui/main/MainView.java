package com.huflit.goldtracker.ui.main;

import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

import java.util.List;

public interface MainView {

    void onLoadDataSuccess(TyGiaResponse tyGiaResponse);

    void onLoadDataFailed();

    void onLoadCoinSuccess(List<Coin> coins);

    void onLoadCoinFailed();
}
