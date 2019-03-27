package com.nju.tourSystem.mapper;

import com.nju.tourSystem.entity.Participant;
import com.nju.tourSystem.mapper.provider.ParticipantProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ParticipantMapper {

    @Select("SELECT * FROM participant WHERE id = #{id}")
    Participant getParticipantById(int id);

    @Select("SELECT * FROM participant WHERE uid = #{uid} and agree = '未通过'")
    List<Participant> getRefusedListByUid(String uid);

    @Select("SELECT * FROM participant WHERE uid = #{uid} and agree = '已通过'")
    List<Participant> getActivityListByUid(String uid);

    @Select("SELECT * FROM participant WHERE uid = #{uid} and agree = '待审核'")
    List<Participant> getApplicationListByUid(String uid);

    @Select("SELECT * FROM participant WHERE aid = #{aid} and agree = '未通过'")
    List<Participant> getRefusedList(int aid);

    @Select("SELECT * FROM participant WHERE aid = #{aid} and agree = '已通过'")
    List<Participant> getParticipantList(int aid);

    @Select("SELECT * FROM participant WHERE aid = #{aid} and agree = '待审核'")
    List<Participant> getApplicationList(int aid);

    @Delete("DELETE FROM participant WHERE id = #{id}")
    Boolean delete(int id);

    @InsertProvider(type = ParticipantProvider.class,method = "insert")
    Boolean insert(@Param("participant") Participant participant);

    @UpdateProvider(type = ParticipantProvider.class,method = "update")
    Boolean update(@Param("participant")Participant participant);

    @Select("SELECT aid FROM participant GROUP BY aid ORDER BY count(aid) DESC")
    List<Integer> searchHotActivity();

}
