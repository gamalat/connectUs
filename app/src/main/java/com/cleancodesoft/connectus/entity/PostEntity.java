package com.cleancodesoft.connectus.entity;

public class PostEntity implements Entity {

    String id;
    String text;
    String imagePath;
    String postPrivacy;

    public PostEntity() {
    }

    public PostEntity(String id, String text, String imagePath, String postPrivacy) {
        this.id = id;
        this.text = text;
        this.imagePath = imagePath;
        this.postPrivacy = postPrivacy;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public Entity getNewEntity() {
        return new PostEntity();
    }
}
