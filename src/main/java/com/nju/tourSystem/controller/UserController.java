package com.nju.tourSystem.controller;


import com.nju.tourSystem.PO.UserInfo;
import com.nju.tourSystem.entity.JsonResponse;
import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("用户信息管理")
@RestController
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    UserService userService;
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    /**
     *
     * @author yqe
     */
    @ApiOperation("根据ID获取用户信息")
    @RequestMapping(value = "userinfo/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getUser(@PathVariable String id) {
        JsonResponse r = new JsonResponse();
        try {
            User user = userService.getUserById(id);
            r.setData(user);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     *
     * @author yqe
     */
    @ApiOperation("获取用户列表")
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getUserList() {
        JsonResponse r = new JsonResponse();
        try {
            List<User> userList = userService.getAllUser();
            r.setData(userList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     *
     * @author yqe
     */
    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> updateUser(@RequestBody UserInfo userInfo) {
        JsonResponse r = new JsonResponse();
        try {
            String uid = userInfo.getUserid();
            User user = userService.getUserById(uid);
            switch (userInfo.getColumnid()){
                case "年龄":
                    user.setAge(Integer.valueOf(userInfo.getValue()));
                    break;
                case "邮箱":
                    user.setEmail(userInfo.getValue());
                    break;
                case "电话号码":
                    user.setPhone(userInfo.getValue());
                    break;
                case "个人简介":
                    user.setDescription(userInfo.getValue());
                    break;
            }

            Boolean state = userService.updateUser(user);
            r.setData(state);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     *
     * @author yqe
     */
    @ApiOperation("查找用户")
    @RequestMapping(value = "/searchUser/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> login(@PathVariable("keyword") String keyword) {
        JsonResponse r = new JsonResponse();
        try {
            List<User> userList = userService.searchUser(keyword);
            r.setData(userList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }



}
