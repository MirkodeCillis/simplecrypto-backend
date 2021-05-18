package com.simplecrypto.server.models;

public class CommentModel {
    private Integer postId;

    private Integer userId;

    private String message;

    public CommentModel(Integer postId, Integer userId, String message) {
        this.postId = postId;
        this.userId = userId;
        this.message = message;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
