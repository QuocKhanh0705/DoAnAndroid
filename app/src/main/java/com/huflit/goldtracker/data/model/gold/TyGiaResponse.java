package com.huflit.goldtracker.data.model.gold;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TyGiaResponse {
    @Expose
    @SerializedName("rates")
    private List<BaseExchange> rates;
    @Expose
    @SerializedName("golds")
    private List<BaseGold> golds;

    public List<BaseGold> getGolds() {
        return golds;
    }

    public List<BaseExchange> getExchanges() {
        return rates;
    }
}
