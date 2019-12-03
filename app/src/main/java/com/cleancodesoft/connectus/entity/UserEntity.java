package com.cleancodesoft.connectus.entity;

public class UserEntity implements Entity {

    String id;
    String userName;
    String userSubdata;
    String userImage;
    String friendshipStatus;
    String duration;

    public UserEntity(String id, String userName, String userSubdata, String userImage, String friendshipStatus, String duration) {
        this.id = id;
        this.userName = userName;
        this.userSubdata = userSubdata;
        this.userImage = userImage;
        this.friendshipStatus = friendshipStatus;
        this.duration = duration;
    }

    public UserEntity() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Entity getNewEntity() {
        return new UserEntity();
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
