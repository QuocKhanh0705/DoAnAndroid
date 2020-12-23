package com.huflit.goldtracker.data.model.coin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huflit.goldtracker.data.model.gold.BaseGold;

import java.util.List;

public class CoinResponse {
    private List<Coin> coins;

    public List<Coin> getCoins() {
        return coins;
    }
}
