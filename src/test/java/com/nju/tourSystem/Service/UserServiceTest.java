package com.nju.tourSystem.Service;

import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    /**
     * 测试根据ID获取用户信息
     */
    @Test
    public void getUserByIdTest() throws Exception {
        String userId = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        User user = userService.getUserById(userId);
        Assert.assertEquals("Eternal",user.getUsername());
    }

    /**
     * 测试返回所有用户列表
     */
    @Test
    public void getAllUserTest() throws Exception {
        userService.getAllUser();
    }

    /**
     * 测试查找用户
     */
    @Test
    public void searchUserTest() throws Exception {
        userService.searchUser("剑");
    }

    /**
     * 测试更新用户
     */
    @Test
    public void updateUserTest() throws Exception {
        String userId = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        User user = userService.getUserById(userId);
        user.setAge(25);
        Assert.assertTrue(userService.updateUser(user));
    }

}
