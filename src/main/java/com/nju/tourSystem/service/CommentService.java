package com.nju.tourSystem.service;

import com.nju.tourSystem.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    Comment getCommentById(int id);
    List<Comment> getCommentListByReceiverUid(String receiverUid);
    Boolean addComment(Comment comment);
    Boolean updateComment(Comment comment);
}
