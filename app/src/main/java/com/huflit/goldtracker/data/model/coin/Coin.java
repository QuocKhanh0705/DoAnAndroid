package com.huflit.goldtracker.data.model.coin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin {
    @Expose
    @SerializedName("id") //api
    private String id; //app
    @Expose
    @SerializedName("symbol")
    private String symbol;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("current_price")
    private double currentPrice;
    @Expose
    @SerializedName("market_cap_rank")
    private long marketCapRank;
    @Expose
    @SerializedName("price_change_24h")
    private double priceChange24h;
    @Expose
    @SerializedName("price_change_percentage_24h")
    private double priceChangePercentage24h;
    @Expose
    @SerializedName("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;
    @Expose
    @SerializedName("ath_change_percentage")
    private double athChangePercentage;
    @Expose
    @SerializedName("ath_date")
    private String athDate;
    @Expose
    @SerializedName("last_updated")
    private String lastUpdated;
    @Expose
    @SerializedName("price_change_percentage_1h_in_currency")
    private double priceChangePercentage1hInCurrency;
    @Expose
    @SerializedName("price_change_percentage_24h_in_currency")
    private double priceChangePercentage24hInCurrency;
    @Expose
    @SerializedName("price_change_percentage_7d_in_currency")
    private double priceChangePercentage7dInCurrency;
    @Expose
    @SerializedName("price_change_percentage_30d_in_currency")
    private double priceChangePercentage30dInCurrency;
    @Expose
    @SerializedName("price_change_percentage_1y_in_currency")
    private double priceChangePercentage1yInCurrency;

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public long getMarketCapRank() {
        return marketCapRank;
    }

    public double getPriceChange24h() {
        return priceChange24h;
    }

    public double getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public double getMarketCapChangePercentage24h() {
        return marketCapChangePercentage24h;
    }

    public double getAthChangePercentage() {
        return athChangePercentage;
    }

    public String getAthDate() {
        return athDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }


    //ham
    public double getPriceChangePercentage1hInCurrency() {
        return priceChangePercentage1hInCurrency;
    }

    public double getPriceChangePercentage24hInCurrency() {
        return priceChangePercentage24hInCurrency;
    }

    public double getPriceChangePercentage7dInCurrency() {
        return priceChangePercentage7dInCurrency;
    }

    public double getPriceChangePercentage30dInCurrency() {
        return priceChangePercentage30dInCurrency;
    }

    public double getPriceChangePercentage1yInCurrency() {
        return priceChangePercentage1yInCurrency;
    }

    private static String[] percentageSymbols = {"1h", "24h", "7d", "30d", "1y"};
    private static String[] percentageTitles = {"1 hour", "24 hour", "7 days", "30 days", "1 year"};

    public static String[] getPercentageSymbols() {
        return percentageSymbols;
    }

    public static String[] getPercentageTitles() {
        return percentageTitles;
    }
}
