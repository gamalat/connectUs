package com.cleancodesoft.connectus.entity;

public class PostjoinEntity implements Entity {
    String id;
    String userName;
    String userImage;
    String text;
    String imagePath;
    String postPrivacy;

    public PostjoinEntity() {
    }

    public PostjoinEntity(String id, String userName, String userImage, String text, String imagePath, String postPrivacy) {
        this.id = id;
        this.userName = userName;
        this.userImage = userImage;
        this.text = text;
        this.imagePath = imagePath;
        this.postPrivacy = postPrivacy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPostPrivacy() {
        return postPrivacy;
    }

    public void setPostPrivacy(String postPrivacy) {
        this.postPrivacy = postPrivacy;
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
        return new PostjoinEntity();
    }
}
