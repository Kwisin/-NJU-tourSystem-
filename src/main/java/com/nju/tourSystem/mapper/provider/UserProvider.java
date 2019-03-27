package com.nju.tourSystem.mapper.provider;

import com.nju.tourSystem.entity.User;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class UserProvider {
    private static final String TABLE_NAME="user";
    public String insert(Map<String,Object> para){
        User user = (User) para.get("user");
        if(user!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("id","#{user.id,javaType=String,jdbcType=VARCHAR}");
            VALUES("username","#{user.username,javaType=String,jdbcType=VARCHAR}");
            VALUES("email","#{user.email,javaType=String,jdbcType=VARCHAR}");
            VALUES("portrait","#{user.portrait,javaType=String,jdbcType=VARCHAR}");
            VALUES("phone","#{user.phone,javaType=String,jdbcType=VARCHAR}");
            VALUES("description","#{user.description,javaType=String,jdbcType=VARCHAR}");
            VALUES("age","#{user.age,javaType=int,jdbcType=INTEGER}");
            VALUES("balance","#{user.balance,javaType=double,jdbcType=DOUBLE}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        User user = (User) para.get("user");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("username = #{user.username,javaType=String,jdbcType=VARCHAR}");
        SET("portrait = #{user.portrait,javaType=String,jdbcType=VARCHAR}");
        SET("phone = #{user.phone,javaType=String,jdbcType=VARCHAR}");
        SET("email = #{user.email,javaType=String,jdbcType=VARCHAR}");
        SET("description = #{user.description,javaType=String,jdbcType=VARCHAR}");
        SET("age = #{user.age,javaType=int,jdbcType=INTEGER}");
        SET("balance = #{user.balance,javaType=double,jdbcType=DOUBLE}");
        WHERE("id = #{user.id,javaType=String,jdbcType=VARCHAR}");
        return SQL();
    }
}
