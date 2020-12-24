package com.huflit.goldtracker.data.model.gold;

import com.huflit.goldtracker.utils.NumberUtils;

public class Exchange {
    private String buy;
    private String order;
    private String name;
    private String sell;
    private String transfer;
    private String id;
    private String day;
    private String code;

    public double getBuy() {
        return NumberUtils.parseDouble(buy);
    }

    public String getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public double getSell() {
        return NumberUtils.parseDouble(sell);
    }

    public double getTransfer() {
        return NumberUtils.parseDouble(transfer);
    }

    public String getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getCode() {
        return code;
    }
}
