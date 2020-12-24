package com.huflit.goldtracker.utils;

import android.content.res.Resources;

import java.text.DecimalFormat;

public class CurrencyUtils {

    public CurrencyUtils() {
    }

    public final String CURRENCY_VND_UNIT = "đ";
    public final String CURRENCY_MIL_UNIT = "tr";

    private final DecimalFormat formatter = new DecimalFormat("#,###");

    public String format(Double currency) {
        return String.format("%s %s", toCurrency(currency), CURRENCY_VND_UNIT);
    }

    public String format(Double currency, String unit) {
        return String.format("%s %s", toCurrency(currency), unit);
    }

    private String toCurrency(Double currency) {
        return formatter.format(currency);
    }
}
