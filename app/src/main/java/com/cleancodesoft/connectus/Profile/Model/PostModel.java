package com.cleancodesoft.connectus.Profile.Model;

public class PostModel {
    String id ;
    String userName;
    String userImge;
    String postText;
    String postImage;

    public PostModel(String id, String userName, String userImge, String postText, String postImage) {
        this.id = id;
        this.userName = userName;
        this.userImge = userImge;
        this.postText = postText;
        this.postImage = postImage;
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

    public String getUserImge() {
        return userImge;
    }

    public void setUserImge(String userImge) {
        this.userImge = userImge;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
