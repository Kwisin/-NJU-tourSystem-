package com.nju.tourSystem.mapper.provider;

import com.nju.tourSystem.entity.TourRecord;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class TourRecordProvider {
    private static final String TABLE_NAME="tourRecord";
    public String insert(Map<String,Object> para){
        TourRecord tourRecord = (TourRecord) para.get("tourRecord");
        if(tourRecord!=null){
            BEGIN();
            INSERT_INTO(TABLE_NAME);
            VALUES("uid","#{tourRecord.uid,javaType=String,jdbcType=VARCHAR}");
            VALUES("title","#{tourRecord.title,javaType=String,jdbcType=VARCHAR}");
            VALUES("content","#{tourRecord.content,javaType=String,jdbcType=VARCHAR}");
            VALUES("publishTime","#{tourRecord.publishTime,javaType=String,jdbcType=VARCHAR}");
            VALUES("readNum","#{tourRecord.readNum,javaType=int,jdbcType=INTEGER}");
        }
        return SQL();

    }


    public String update(Map<String,Object> para){
        TourRecord tourRecord = (TourRecord) para.get("tourRecord");
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("uid = #{tourRecord.uid,javaType=String,jdbcType=VARCHAR}");
        SET("title = #{tourRecord.title,javaType=String,jdbcType=VARCHAR}");
        SET("content = #{tourRecord.content,javaType=String,jdbcType=VARCHAR}");
        SET("publishTime = #{tourRecord.publishTime,javaType=String,jdbcType=VARCHAR}");
        SET("readNum = #{tourRecord.readNum,javaType=int,jdbcType=INTEGER}");
        WHERE("id = #{tourRecord.id,javaType=int,jdbcType=INTEGER}");
        return SQL();
    }
}
