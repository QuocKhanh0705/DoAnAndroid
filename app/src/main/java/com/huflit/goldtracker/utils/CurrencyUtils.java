package com.huflit.goldtracker.utils;

import android.content.res.Resources;

import java.text.DecimalFormat;

public class CurrencyUtils {

    public static final String CURRENCY_VND_UNIT = "đ";
    public static final String CURRENCY_MIL_UNIT = "tr";

    private static final DecimalFormat formatter = new DecimalFormat("##.####");

    public static String format(double currency) {
        return String.format("%s %s", toCurrency(currency), CURRENCY_VND_UNIT);
    }

    public static String format(double currency, String unit) {
        return String.format("%s %s", toCurrency(currency), unit);
    }

    public static String formatFullDigit(double currency) {
        return String.format("$%s", currency);
    }

    public static String percentFormat(double percent) {
        return formatter.format(percent) + "%";
    }

    private static String toCurrency(Double currency) {
        return formatter.format(currency);
    }
}
