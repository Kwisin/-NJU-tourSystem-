package com.nju.tourSystem.mapper;

import com.nju.tourSystem.entity.Friendship;
import com.nju.tourSystem.mapper.provider.FriendshipProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FriendshipMapper {

    @Select("SELECT * FROM friendship WHERE id = #{id}")
    Friendship getFriendshipById(int id);

    @Select("SELECT * FROM friendship WHERE uid = #{uid} and agree = 1")
    List<Friendship> getFriendshipListByUid(String uid);

    @Select("SELECT * FROM friendship WHERE uid = #{uid} and agree = 0")
    List<Friendship> getApplicationListByUid(String uid);

    @Delete("DELETE FROM friendship WHERE id = #{id}")
    Boolean delete(int id);

    @InsertProvider(type = FriendshipProvider.class,method = "insert")
    Boolean insert(@Param("friendship") Friendship friendship);

    @UpdateProvider(type = FriendshipProvider.class,method = "update")
    Boolean update(@Param("friendship")Friendship friendship);
}
