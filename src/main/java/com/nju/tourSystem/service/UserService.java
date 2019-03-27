package com.nju.tourSystem.service;

import com.nju.tourSystem.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(String id);
    User getUserByEmail(String email);
    List<User> getAllUser();
    List<User> searchUser(String keyword);
    Boolean addUser(User user);
    Boolean updateUser(User user);
}
