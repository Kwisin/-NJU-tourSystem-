package com.nju.tourSystem.mapper.provider;

import com.nju.tourSystem.entity.Comment;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class CommentProvider {
    private static final String TABLE_NAME="comment";
    public String insert(Map<String,Object> para){
        Comment comment = (Comment) para.get("comment");
        if(comment!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("uid","#{comment.uid,javaType=String,jdbcType=VARCHAR}");
            VALUES("aid","#{comment.aid,javaType=int,jdbcType=INTEGER}");
            VALUES("activityName","#{comment.activityName,javaType=String,jdbcType=VARCHAR}");
            VALUES("receiverUid","#{comment.receiverUid,javaType=String,jdbcType=VARCHAR}");
            VALUES("comment","#{comment.comment,javaType=String,jdbcType=VARCHAR}");
            VALUES("commentTime","#{comment.commentTime,javaType=String,jdbcType=VARCHAR}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        Comment comment = (Comment) para.get("comment");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("uid = #{comment.uid,javaType=String,jdbcType=VARCHAR}");
        SET("aid = #{comment.aid,javaType=int,jdbcType=INTEGER}");
        SET("activityName = #{comment.activityName,javaType=String,jdbcType=VARCHAR}");
        SET("receiverUid = #{comment.receiverUid,javaType=String,jdbcType=VARCHAR}");
        SET("comment = #{comment.comment,javaType=String,jdbcType=VARCHAR}");
        SET("commentTime = #{comment.commentTime,javaType=String,jdbcType=VARCHAR}");
        WHERE("id = #{comment.id,javaType=int,jdbcType=INTEGER}");
        return SQL();
    }
}
