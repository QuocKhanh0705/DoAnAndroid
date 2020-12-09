package com.huflit.goldtracker.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoldResponse {
    @Expose
    @SerializedName("golds")
    private List<BaseGold> golds;

    public List<BaseGold> getGolds() {
        return golds;
    }
}
