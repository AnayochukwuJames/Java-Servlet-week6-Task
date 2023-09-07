package com.example.facebook.Model;

public class Posts {
    private int postId;
    private int userId;
    public String content;
    public int dateCreated;



    public Posts(int postId, int id, String content) {
        this.postId = postId;
        this.content = content;
    }

    public Posts() {

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId() {
    }
}