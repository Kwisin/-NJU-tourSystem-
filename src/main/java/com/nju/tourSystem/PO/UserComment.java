package com.nju.tourSystem.PO;

import com.nju.tourSystem.entity.Comment;
import com.nju.tourSystem.entity.User;

import java.util.List;

public class UserComment {
    private User user;
    private List<Comment> commentList;

    public UserComment() {
    }

    public UserComment(User user, List<Comment> commentList) {
        this.user = user;
        this.commentList = commentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
