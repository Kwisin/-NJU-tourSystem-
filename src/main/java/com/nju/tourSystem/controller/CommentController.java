package com.nju.tourSystem.controller;

import com.nju.tourSystem.PO.UserComment;
import com.nju.tourSystem.entity.Comment;
import com.nju.tourSystem.entity.JsonResponse;
import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.CommentService;
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

@Api("留言管理")
@RestController
@RequestMapping("/comment/*")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @author yqe
     */
    @ApiOperation("根据ID获取评论")
    @RequestMapping(value = "commentInfo/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> commentInfo(@PathVariable int id) {
        JsonResponse r = new JsonResponse();
        try {
            Comment comment = commentService.getCommentById(id);
            r.setData(comment);
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
    @ApiOperation("添加评论")
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> addComment(@RequestBody Comment comment) {
        JsonResponse r = new JsonResponse();
        try {
            Boolean state = commentService.addComment(comment);
            r.setData(state);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

//    /**
//     *
//     * @author yqe
//     */
//    @ApiOperation("更新评论")
//    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
//    public ResponseEntity<JsonResponse> updateComment(@RequestBody Comment comment) {
//        JsonResponse r = new JsonResponse();
//        try {
//            Boolean state = commentService.updateComment(comment);
//            r.setData(state);
//            r.setStatus("ok");
//        } catch (Exception e) {
//            r.setData(e.getClass().getName() + ":" + e.getMessage());
//            r.setStatus("error");
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok(r);
//    }

    /**
     * @author yqe
     * 获取用户留言列表PO
     */
    private List<UserComment> getUserComment(List<Comment> commentList) {
        List<UserComment> userList = new ArrayList<>();
        List<String> userIdList = new ArrayList<>();
        int index = -1;
        for (Comment comment : commentList) {
            if (userIdList.contains(comment.getUid())) {
                index = userIdList.indexOf(comment.getUid());
                List<Comment> userCommentList = userList.get(index).getCommentList();
                userCommentList.add(comment);
                userList.get(index).setCommentList(userCommentList);
            } else {
                String uid = comment.getUid();
                userIdList.add(uid);
                User user = userService.getUserById(uid);
                List<Comment> userCommentList = new ArrayList<>();
                userCommentList.add(comment);
                UserComment userComment = new UserComment(user, userCommentList);
                userList.add(userComment);
            }
        }
        return userList;
    }

    /**
     * @author yqe
     */
    @ApiOperation("根据接收者ID获取新留言")
    @RequestMapping(value = "newCommentInfoList/{receiverID}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> commentInfo(@PathVariable String receiverID) {
        JsonResponse r = new JsonResponse();
        try {
            List<Comment> commentList = commentService.getCommentListByReceiverUid(receiverID);
            List<UserComment> userList = getUserComment(commentList);
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
