package com.huflit.goldtracker.data.model.gold;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huflit.goldtracker.utils.NumberUtils;

public class Gold {
    @Expose
    @SerializedName("buy")
    private String buy;
    @Expose
    @SerializedName("sell")
    private String sell;
    @Expose
    @SerializedName("brand")
    private String brand;
    @Expose
    @SerializedName("company")
    private String company;
    @Expose
    @SerializedName("updated")
    private String updated;
    @Expose
    @SerializedName("brand1")
    private String brand1;
    @Expose
    @SerializedName("day")
    private String day;
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("code")
    private String code;

    public Gold() {
    }

    public double getBuy() {
        return NumberUtils.parseDouble(buy);
    }

    public double getSell() {
        return NumberUtils.parseDouble(sell);
    }

    public String getBrand() {
        return brand;
    }

    public String getCompany() {
        return company;
    }

    public String getUpdated() {
        return updated;
    }

    public String getBrand1() {
        return brand1;
    }

    public String getDay() {
        return day;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Gold{" +
                "buy='" + buy + '\'' +
                ", sell='" + sell + '\'' +
                ", brand='" + brand + '\'' +
                ", company='" + company + '\'' +
                ", updated='" + updated + '\'' +
                ", brand1='" + brand1 + '\'' +
                ", day='" + day + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
