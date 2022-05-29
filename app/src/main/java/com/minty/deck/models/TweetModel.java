package com.minty.deck.models;

public class TweetModel {
    private int id;
    private String tweet;
    private String imageUrl;
    private UserModel user;

    public TweetModel(String tweet, String imageUrl, UserModel user) {
        this.tweet = tweet;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
