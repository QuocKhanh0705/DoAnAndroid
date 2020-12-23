package com.huflit.goldtracker.data.model.gold;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseRate {
    private String bank;
    private String brand;
    private long updated;
    private String date;
    private String version;
    @Expose
    @SerializedName("value")
    private List<Rate> rates;

    public String getBank() {
        return bank;
    }

    public String getBrand() {
        return brand;
    }

    public long getUpdated() {
        return updated;
    }

    public String getDate() {
        return date;
    }

    public String getVersion() {
        return version;
    }

    public List<Rate> getRates() {
        return rates;
    }
}
