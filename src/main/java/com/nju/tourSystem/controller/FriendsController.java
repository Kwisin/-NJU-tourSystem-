package com.nju.tourSystem.controller;

import com.nju.tourSystem.entity.Friendship;
import com.nju.tourSystem.entity.JsonResponse;
import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.FriendshipService;
import com.nju.tourSystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("好友系统管理")
@RestController
@RequestMapping("/friends/*")
public class FriendsController {
    @Autowired
    FriendshipService friendshipService;
    @Autowired
    UserService userService;

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    /**
     *
     * @author yqe
     */
    @ApiOperation("添加好友")
    @RequestMapping(value = "/addFriends/{uid},{fid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> addFriends(@PathVariable("uid") String uid,@PathVariable("fid") String fid) {
        JsonResponse r = new JsonResponse();
        try {
            Friendship friendship = new Friendship();
            friendship.setUid(uid);
            friendship.setFid(fid);
            friendship.setAgree(false);
            Boolean state = friendshipService.addFriend(friendship);
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
    @ApiOperation("同意添加好友")
    @RequestMapping(value = "/friendsAgreement/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> friendsAgreement(@PathVariable("id") int id) {
        JsonResponse r = new JsonResponse();
        try {
            Friendship friendship = friendshipService.getFriendshipById(id);
            friendship.setAgree(true);
            Boolean state = friendshipService.updateFriend(friendship);
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
    @ApiOperation("获取好友列表")
    @RequestMapping(value = "/friendsList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> friendsList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        try {
            List<Friendship> friendshipList = friendshipService.getFriendshipListByUid(uid);
            List<User> userList = new ArrayList<>();
            for(Friendship friendship:friendshipList){
                userList.add(userService.getUserById(friendship.getFid()));
            }
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
    @ApiOperation("获取好友申请列表")
    @RequestMapping(value = "/applicationList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> applicationList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        try {
            List<Friendship> friendshipList = friendshipService.getApplicationListByUid(uid);
            r.setData(friendshipList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

}
