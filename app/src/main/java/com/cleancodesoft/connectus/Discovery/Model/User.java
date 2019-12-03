package com.cleancodesoft.connectus.Discovery.Model;

public class User {
    String id;
    String userName;
    String userSubdata;
    String userImage;
    String friendshipStatus;

    public User(String id, String userName, String userSubdata, String userImage, String friendshipStatus) {
        this.id = id;
        this.userName = userName;
        this.userSubdata = userSubdata;
        this.userImage = userImage;
        this.friendshipStatus = friendshipStatus;
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

    public String getUserSubdata() {
        return userSubdata;
    }

    public void setUserSubdata(String userSubdata) {
        this.userSubdata = userSubdata;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getFriendshipStatus() {
        return friendshipStatus;
    }

    public void setFriendshipStatus(String friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }
}
