package com.huflit.goldtracker.utils;

public class NumberUtils {

    private static String beautyStringNumber(String input) {
        return input.replace(",", "").replace(".", "");
    }

    public static Double parseDouble(String number) {
        return Double.parseDouble(beautyStringNumber(number)) / 100;
    }
}
