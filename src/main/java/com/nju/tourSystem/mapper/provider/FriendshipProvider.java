package com.nju.tourSystem.mapper.provider;

import com.nju.tourSystem.entity.Friendship;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class FriendshipProvider {
    private static final String TABLE_NAME="friendship";
    public String insert(Map<String,Object> para){
        Friendship friendship = (Friendship) para.get("friendship");
        if(friendship!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("uid","#{friendship.uid,javaType=String,jdbcType=VARCHAR}");
            VALUES("fid","#{friendship.fid,javaType=String,jdbcType=VARCHAR}");
            VALUES("agree","#{friendship.agree,javaType=boolean,jdbcType=TINYINT}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        Friendship friendship = (Friendship) para.get("friendship");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("uid = #{friendship.uid,javaType=String,jdbcType=VARCHAR}");
        SET("fid = #{friendship.fid,javaType=String,jdbcType=VARCHAR}");
        SET("agree = #{friendship.agree,javaType=boolean,jdbcType=TINYINT}");
        WHERE("id = #{friendship.id,javaType=int,jdbcType=INTEGER}");
        return SQL();
    }
}
