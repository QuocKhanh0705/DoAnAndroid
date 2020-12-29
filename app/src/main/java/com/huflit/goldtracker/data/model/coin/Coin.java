package com.huflit.goldtracker.data.model.coin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin {
    @Expose
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


    public  String  getId(){
        return  id;
    }
    public  String  getFullName(){
        return  fullName;
    }
    public  String  getCurrent_Price(){
        return  Current_Price;
    }
    public  String  getChange_Price_24h_Percentage(){
        return  Change_Price_24h_Percentage;
    }
    public Coin() {
    }
   /*
    @Expose
    @SerializedName("crypto")
    private boolean crypto;
    @Expose
    @SerializedName("payinEnabled")
    private boolean payinEnabled;
    @Expose
    @SerializedName("payinPaymentId")
    private boolean payinPaymentId;
    @Expose
    @SerializedName("payinConfirmations")
    private int payinConfirmations;
    @Expose
    @SerializedName("payoutEnabled")
    private boolean payoutEnabled;
    @Expose
    @SerializedName("payoutIsPaymentId")
    private boolean payoutIsPaymentId;
    @Expose
    @SerializedName("transferEnabled")
    private boolean transferEnabled;
    @Expose
    @SerializedName("delisted")
    private boolean delisted;
    @Expose
    @SerializedName("payoutFee")
    private String payoutFee;
    @Expose
    @SerializedName("precisionPayout")
    private int precisionPayout;
    @Expose
    @SerializedName("precisionTransfer")
    private int precisionTransfer;
    @Expose
    @SerializedName("lowProcessingTime")
    private String lowProcessingTime;
    @Expose
    @SerializedName("highProcessingTime")
    private String highProcessingTime;
    @Expose
    @SerializedName("avgProcessingTime")
    private String avgProcessingTime;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

   */

   /* public boolean isCrypto() {
        return crypto;
    }

    public boolean isPayinEnabled() {
        return payinEnabled;
    }

    public boolean isPayinPaymentId() {
        return payinPaymentId;
    }

    public int getPayinConfirmations() {
        return payinConfirmations;
    }

    public boolean isPayoutEnabled() {
        return payoutEnabled;
    }

    public boolean isPayoutIsPaymentId() {
        return payoutIsPaymentId;
    }

    public boolean isTransferEnabled() {
        return transferEnabled;
    }

    public boolean isDelisted() {
        return delisted;
    }

    public String getPayoutFee() {
        return payoutFee;
    }

    public int getPrecisionPayout() {
        return precisionPayout;
    }

    public int getPrecisionTransfer() {
        return precisionTransfer;
    }

    public String getLowProcessingTime() {
        return lowProcessingTime;
    }

    public String getHighProcessingTime() {
        return highProcessingTime;
    }

    public String getAvgProcessingTime() {
        return avgProcessingTime;
    }*/
}
