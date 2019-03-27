package com.nju.tourSystem.mapper;

import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.mapper.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(String id);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User getUserByEmail(String email);

    @Select("SELECT * FROM user ")
    List<User> getAllUser();

    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%',#{keyword},'%')")
    List<User> searchUser(String keyword);

    @InsertProvider(type = UserProvider.class,method = "insert")
    Boolean insert(@Param("user") User user);

    @UpdateProvider(type = UserProvider.class,method = "update")
    Boolean update(@Param("user")User user);

}
