package com.nju.tourSystem.mapper;

import com.nju.tourSystem.entity.Activity;
import com.nju.tourSystem.mapper.provider.ActivityProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ActivityMapper {

    @Select("SELECT * FROM activity WHERE id = #{id}")
    Activity getActivityById(int id);

    @Select("SELECT * FROM activity WHERE organizerId = #{organizerId}")
    List<Activity> getActivityByOrganizerId(String organizerId);

    @Select("SELECT * FROM activity ")
    List<Activity> getAllActivity();

    @Select("SELECT * FROM activity WHERE deadline > #{date} and #{date} >= createdTime")
    List<Activity> getNewActivity(String date);

    @Select("SELECT * FROM activity WHERE endTime > #{date} and #{date} >= startTime")
    List<Activity> getOngoingActivity(String date);

    @Select("SELECT * FROM activity WHERE endTime <= #{date}")
    List<Activity> getFinishedActivity(String date);

    @Select("SELECT * FROM activity WHERE name LIKE CONCAT('%',#{keyword},'%')")
    List<Activity> searchActivity(String keyword);

    @InsertProvider(type = ActivityProvider.class,method = "insert")
    Boolean insert(@Param("activity")Activity activity);

    @UpdateProvider(type = ActivityProvider.class,method = "update")
    Boolean update(@Param("activity")Activity activity);
}
