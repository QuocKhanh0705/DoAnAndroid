package com.huflit.goldtracker.data.model.gold;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseExchange {
    @Expose
    @SerializedName("bank")
    private String bank;
    @Expose
    @SerializedName("brand")
    private String brand;
    @Expose
    @SerializedName("updated")
    private long updated;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("version")
    private String version;
    @Expose
    @SerializedName("value")
    private List<Exchange> exchanges;

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

    public List<Exchange> getExchanges() {
        return exchanges;
    }
}
