package com.nju.tourSystem.service.serviceImpl;

import com.nju.tourSystem.entity.Comment;
import com.nju.tourSystem.mapper.CommentMapper;
import com.nju.tourSystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment getCommentById(int id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public List<Comment> getCommentListByReceiverUid(String receiverUid) {
        return commentMapper.getCommentListByReceiverUid(receiverUid);
    }


    @Override
    public Boolean addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public Boolean updateComment(Comment comment) {
        return commentMapper.update(comment);
    }
}
