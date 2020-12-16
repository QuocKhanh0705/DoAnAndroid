package com.huflit.goldtracker.data.model.gold;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseGold {
    @Expose
    @SerializedName("updated")
    private String updated;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("value")
    private List<Gold> golds;

    public String getUpdated() {
        return updated;
    }

    public String getDate() {
        return date;
    }

    public List<Gold> getGolds() {
        return golds;
    }
}
