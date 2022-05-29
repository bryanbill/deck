package com.minty.deck.models;

public class HashtagModel {
    private int id;
    private String hashtag;
    private int count;

    public HashtagModel(String hashtag, int count) {

        this.hashtag = hashtag;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

