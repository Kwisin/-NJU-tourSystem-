package com.nju.tourSystem.service.serviceImpl;

import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.mapper.UserMapper;
import com.nju.tourSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public List<User> searchUser(String keyword) {
        return userMapper.searchUser(keyword);
    }

    @Override
    public Boolean addUser(User user) {
        if(getUserById(user.getId()) != null)
            return updateUser(user);
        else
            return userMapper.insert(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.update(user);
    }

}
