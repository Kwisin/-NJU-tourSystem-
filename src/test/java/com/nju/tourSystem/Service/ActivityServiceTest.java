package com.nju.tourSystem.Service;

import com.nju.tourSystem.entity.Activity;
import com.nju.tourSystem.service.ActivityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceTest {
    @Autowired
    ActivityService activityService;

    /**
     * 测试根据ID获取活动信息
     */
    @Test
    public void getActivityByIdTest() throws Exception {
        Activity activity = activityService.getActivityById(2);
        Assert.assertEquals("西藏",activity.getAddress());
    }

    /**
     * 测试获取所有活动列表
     */
    @Test
    public void getAllActivityTest() throws Exception {
       activityService.getAllActivity();
    }

    /**
     * 测试查找活动
     */
    @Test
    public void searchActivityTest() throws Exception {
        activityService.searchActivity("上海");
    }

     /**
     * 测试根据组织者ID获取活动列表
     */
    @Test
    public void getActivityByOrganizerIdTest() throws Exception {
        String organizerId = "odTDj5MBsaJEVCPe5ebIEZ5UlEPQ";
        activityService.getActivityByOrganizerId(organizerId);
    }

    /**
     * 测试根据时间获取不同状态的活动列表
     */
    @Test
    public void getActivityTypeTest() throws Exception {
        String date = "2019-02-03";
        activityService.getNewActivity(date);
        activityService.getOngoingActivity(date);
        activityService.getFinishedActivity(date);
    }

    /**
     * 测试添加活动
     */
    @Test
    public void addAcitvityTest() throws Exception {
        Activity activity = new Activity();
        activity.setAddress("苏州");
        activity.setOrganizerId("odTDj5GXFvXz01PdRj0NMHQhBqBc");
        Assert.assertTrue(activityService.addAcitvity(activity));
    }

    /**
     * 测试更新活动
     */
    @Test
    public void updateAcitvityTest() throws Exception {
        Activity activity = activityService.getActivityById(18);
        activity.setAddress("台湾");
        Assert.assertTrue(activityService.updateAcitvity(activity));
    }


}
