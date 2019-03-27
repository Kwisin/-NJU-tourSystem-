package com.nju.tourSystem.controller;

import com.nju.tourSystem.PO.ApplicationUser;
import com.nju.tourSystem.entity.Activity;
import com.nju.tourSystem.entity.JsonResponse;
import com.nju.tourSystem.entity.Participant;
import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.ActivityService;
import com.nju.tourSystem.service.ParticipantService;
import com.nju.tourSystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api("活动信息管理")
@RestController
@RequestMapping("/activity/*")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    UserService userService;
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    /**
     *
     * @author yqe
     */
    @ApiOperation("根据ID获取出游信息")
    @RequestMapping(value = "activityInfo/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getActivity(@PathVariable int id) {
        JsonResponse r = new JsonResponse();
        try {
            Activity activity = activityService.getActivityById(id);
            r.setData(activity);
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
    @ApiOperation("获取出游信息列表")
    @RequestMapping(value = "/activityList", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getActivityList() {
        JsonResponse r = new JsonResponse();
        try {
            List<Activity> activityList = activityService.getAllActivity();
            r.setData(activityList);
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
    @ApiOperation("添加出游")
    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> addActivity(@RequestBody Activity activity) {
        JsonResponse r = new JsonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date createdTime = new Date();
            activity.setCreatedTime(sdf.format(createdTime));
            Boolean state = activityService.addAcitvity(activity);
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
    @ApiOperation("更新出游信息")
    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> updateActivity(@RequestBody Activity activity) {
        JsonResponse r = new JsonResponse();
        try {
            Boolean state = activityService.updateAcitvity(activity);
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
    @ApiOperation("搜寻出游活动")
    @RequestMapping(value = "/searchActivity/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> searchActivity(@PathVariable("keyword")String keyword) {
        JsonResponse r = new JsonResponse();
        try {
            List<Activity> activityList = activityService.searchActivity(keyword);
            r.setData(activityList);
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
    @ApiOperation("参加出游")
    @RequestMapping(value = "/joinActivity/{uid},{aid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> joinActivity(@PathVariable("uid") String uid,@PathVariable("aid") int aid) {
        JsonResponse r = new JsonResponse();
        try {
            Participant participant = new Participant();
            participant.setUid(uid);
            participant.setAid(aid);
            participant.setAgree("待审核");
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            participant.setScore(0);
            participant.setApplyTime(sdf.format(d));
            Boolean state = participantService.addParticipant(participant);
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
     * @author xujiangcheng
     */
    @ApiOperation("参加人数多的出游活动")
    @RequestMapping(value = "/hotActivity", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> hotActivity() {
        JsonResponse r = new JsonResponse();
        try {
            List<Activity> activities = new ArrayList<>();
            List<Integer> aid_list= participantService.searchHotActivity();
            for (Integer anAid_list : aid_list) {
                activities.add(activityService.getActivityById(anAid_list));
            }
            r.setData(activities);
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
    @ApiOperation("出游申请操作")
    @RequestMapping(value = "/applicationAgreement/{id},{agree}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> applicationAgreement(@PathVariable("id") int id,@PathVariable("agree") String agree) {
        //agree 操作分为 已通过 和 未通过
        JsonResponse r = new JsonResponse();
        try {
            Participant participant = participantService.getParticipantById(id);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            participant.setAgree(agree);
            participant.setAgreeTime(sdf.format(d));
            Boolean state = participantService.updateParticipant(participant);
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
    private List<Activity> getActivityList(List<Participant> participantList, List<Activity> stateActivityList) {
        List<Activity> activityList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for (Activity activity : stateActivityList){
            idList.add(activity.getId());
        }
        if(participantList.size() > 0){
            for(Participant participant:participantList){
                Activity activity = activityService.getActivityById(participant.getAid());
                if(idList.contains(activity.getId()))
                    activityList.add(activity);
            }
        }
        return activityList;
    }

    /**
     *
     * @author yqe
     */
    @ApiOperation("获取参加的未开始的出游列表")
    @RequestMapping(value = "/myNewActivityList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> myNewActivityList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<Participant> participantList = participantService.getActivityListByUid(uid);
            List<Activity> newActivityList = activityService.getNewActivity(sdf.format(d));
            List<Activity> activityList = getActivityList(participantList, newActivityList);
            r.setData(activityList);
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
    @ApiOperation("获取参加的进行中的出游列表")
    @RequestMapping(value = "/myOngoingActivityList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> myOngoingActivityList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<Participant> participantList = participantService.getActivityListByUid(uid);
            List<Activity> ongoingActivityList = activityService.getOngoingActivity(sdf.format(d));
            List<Activity> activityList = getActivityList(participantList, ongoingActivityList);
            r.setData(activityList);
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
    @ApiOperation("获取参加的进行中的出游列表")
    @RequestMapping(value = "/myFinishedActivityList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> myFinishedActivityList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<Participant> participantList = participantService.getActivityListByUid(uid);
            List<Activity> finishedActivityList = activityService.getFinishedActivity(sdf.format(d));
            List<Activity> activityList = getActivityList(participantList, finishedActivityList);
            r.setData(activityList);
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
    @ApiOperation("获取申请阶段的出游列表")
    @RequestMapping(value = "/myapplicationList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> myapplicationList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        try {
            List<Participant> participantList = participantService.getApplicationListByUid(uid);
            List<Activity> allList = activityService.getAllActivity();
            List<Activity> activityList = getActivityList(participantList, allList);
            r.setData(activityList);
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
    @ApiOperation("获取被拒绝的出游列表")
    @RequestMapping(value = "/myRefusedList/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> myRefusedList(@PathVariable("uid") String uid) {
        JsonResponse r = new JsonResponse();
        try {
            List<Participant> participantList = participantService.getRefusedListByUid(uid);
            List<Activity> allList = activityService.getAllActivity();
            List<Activity> activityList = getActivityList(participantList, allList);
            r.setData(activityList);
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
    @ApiOperation("获取用户发起的活动")
    @RequestMapping(value = "/myOrganizedActivityList/{organizerId}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> myOrganizedActivityList(@PathVariable("organizerId") String organizerId) {
        JsonResponse r = new JsonResponse();
        try {
            List<Activity> activityList = activityService.getActivityByOrganizerId(organizerId);
            r.setData(activityList);
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
    @ApiOperation("获取报名中的活动")
    @RequestMapping(value = "/getNewActivity/{date}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getNewActivity(@PathVariable("date")String date) {
        JsonResponse r = new JsonResponse();
        try {
            List<Activity> activityList = activityService.getNewActivity(date);
            r.setData(activityList);
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
    @ApiOperation("根据活动ID获取参与者列表")
    @RequestMapping(value = "/getMemberList/{aid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getMemberList(@PathVariable("aid")int aid) {
        JsonResponse r = new JsonResponse();
        try {
            List <Participant> participantList = participantService.getParticipantList(aid);
            List <User> userList = new ArrayList<>();
            for (Participant aParticipantList : participantList)
                userList.add(userService.getUserById(aParticipantList.getUid()));
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
    @ApiOperation("根据活动ID获取申请列表")
    @RequestMapping(value = "/getApplicationList/{aid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getApplicationList(@PathVariable("aid")int aid) {
        JsonResponse r = new JsonResponse();
        try {
            List <Participant> participantList = participantService.getApplicationList(aid);
            List<ApplicationUser> applicationUserList = new ArrayList<>();
            for (Participant participant : participantList){
                User user = userService.getUserById(participant.getUid());
                ApplicationUser applicationUser = new ApplicationUser();
                applicationUser.setId(participant.getId());
                applicationUser.setUsername(user.getUsername());
                applicationUser.setPortrait(user.getPortrait());
                applicationUser.setUid(user.getId());
                applicationUser.setAge(user.getAge());
                applicationUser.setApplyTime(participant.getApplyTime());
                applicationUser.setPhone(user.getPhone());
                applicationUser.setEmail(user.getEmail());

                applicationUserList.add(applicationUser);
            }

            r.setData(applicationUserList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
