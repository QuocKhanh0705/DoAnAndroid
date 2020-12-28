package com.huflit.goldtracker.utils;

import java.util.Calendar;

public class DateUtils {

    public static String getDateString(Calendar calendar) {
        return getDay(calendar) + "/" + getMonth(calendar) + "/" + getYear(calendar);
    }

    public static String getDateString(int year, int monthOfYear, int day) {
        return String.format("%s/%s/%s", format(day), format(monthOfYear + 1), format(year));
    }

    public static String getDateFormat(int year, int monthOfYear, int day) {
        return String.format("%s%s%s", year, format(monthOfYear + 1), format(day));
    }

    public static String getDateFormat(Calendar calendar) {
        return String.format("%s%s%s", getYear(calendar), getMonth(calendar), getDay(calendar));
    }

    public static Calendar getCalendar(int year, int monthOfYear, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, day,0,0,0);
        return calendar;
    }

    private static String getDay(Calendar calendar) {
        return format(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private static String getMonth(Calendar calendar) {
        return format(calendar.get(Calendar.MONTH) + 1);
    }

    private static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    private static String format(int digit) {
        if (digit < 10) {
            return "0" + digit;
        } else {
            return String.valueOf(digit);
        }
    }

}
