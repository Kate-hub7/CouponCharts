package ru.sukhikh.couponcharts.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateModel {
    private final static String INPUT_PATTERN = "E, dd MMM yyyy HH:mm:ss z";

    private final String date;
    private final String shopName;

    public DateModel(String date, String shopName) {
        this.date = date;
        this.shopName = shopName;
    }

    public String getDate() { return date; }

    public String getShopName() { return shopName; }


    public static  Integer formatter(String date) {
        DateFormat inputFormatter = new SimpleDateFormat(INPUT_PATTERN, Locale.US);
        Date parseDate = new Date();
        try {
            parseDate = inputFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return countDays(parseDate);
    }

    private static Integer countDays(Date couponDay){
        Date now = new Date();
        Integer days = Math.abs((int)( (now.getTime() - couponDay.getTime()) / (1000 * 60 * 60 * 24)));
        return (days>365)? 365 : days;
    }
}
