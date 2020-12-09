package com.huflit.goldtracker.utils;

import android.content.res.Resources;

import java.text.DecimalFormat;

public class CurrencyUtils {

    public CurrencyUtils() {
    }

    private final String CURRENCY_UNIT = "VND";

    private final DecimalFormat formatter = new DecimalFormat("#,###");

    public String format(Double currency) {
        return String.format("%s %s", toCurrency(currency), CURRENCY_UNIT);
    }

    public String format(String currency) {
        return format(Double.valueOf(currency));
    }

    private String toCurrency(Double currency) {
        return formatter.format(currency).replace(",", ".");
    }
}
