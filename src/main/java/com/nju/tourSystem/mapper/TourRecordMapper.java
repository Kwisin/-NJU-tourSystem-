package com.nju.tourSystem.mapper;

import com.nju.tourSystem.entity.TourRecord;
import com.nju.tourSystem.mapper.provider.TourRecordProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Mapper
public interface TourRecordMapper {

    @Select("SELECT * FROM tourRecord WHERE id = #{id}")
    TourRecord getTourRecordById(int id);

    @Select("SELECT * FROM tourRecord WHERE uid = #{uid}")
    List<TourRecord> getTourRecordByUid(String uid);

    @InsertProvider(type = TourRecordProvider.class,method = "insert")
    Boolean insert(@Param("tourRecord")TourRecord tourRecord);

    @UpdateProvider(type = TourRecordProvider.class,method = "update")
    Boolean update(@Param("tourRecord")TourRecord tourRecord);
}
