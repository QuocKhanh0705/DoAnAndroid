package com.huflit.goldtracker.data.model.coin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huflit.goldtracker.data.model.gold.Gold;

import java.util.List;

public class BaseCoin {
  /*  @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("fullName")
    private String fullName;

    @Expose
    @SerializedName("Current_Price")
    private  String Current_Price;

    @Expose
    @SerializedName("Change_Price_24h_Percentage")
    private  String Change_Price_24h_Percentage;
*/
    private List<Coin> coins;
    public List<Coin> getCoins() {
        return coins;
    }
}
