package ru.sukhikh.couponcharts.models;

public class NumbersCoupon {

    private final Integer couponNumbers;
    private final String shopName;

    public NumbersCoupon(Integer couponNumbers, String shopName) {
        this.couponNumbers = couponNumbers;
        this.shopName = shopName;
    }

    public Integer getCouponNumbers() { return couponNumbers; }

    public String getShopName() { return shopName; }
}
