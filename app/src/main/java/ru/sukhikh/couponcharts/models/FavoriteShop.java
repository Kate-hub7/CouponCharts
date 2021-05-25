package ru.sukhikh.couponcharts.models;

public class FavoriteShop {
    private final String name;
    private final Integer likes;

    public FavoriteShop(String name, Integer likes) {
        this.name = name;
        this.likes = likes;
    }

    public String getName() { return name; }

    public Integer getLikes() { return likes; }
}
