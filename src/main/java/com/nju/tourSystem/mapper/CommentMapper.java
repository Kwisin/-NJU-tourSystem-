package com.nju.tourSystem.mapper;

import com.nju.tourSystem.entity.Comment;
import com.nju.tourSystem.mapper.provider.CommentProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comment WHERE id = #{id}")
    Comment getCommentById(int id);

    @Select("SELECT * FROM comment WHERE receiverUid = #{receiverUid} ORDER BY commentTime ")
    List<Comment> getCommentListByReceiverUid(String receiverUid);

    @InsertProvider(type = CommentProvider.class,method = "insert")
    Boolean insert(@Param("comment")Comment comment);

    @UpdateProvider(type = CommentProvider.class,method = "update")
    Boolean update(@Param("comment")Comment comment);
}
