package com.minty.deck.models;

public class UserModel {
    private String id;
    private String userName;
    private String displayName;
    private String profileImageUrl;
    private String bio;
    private String location;
    private static UserModel userModel;
    public UserModel(String id,String userName,
                     String displayName, String profileImageUrl,
                     String bio, String location) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
        this.location = location;
        userModel = this;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static UserModel getModel() {
        return userModel;
    }
}
