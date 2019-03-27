package com.nju.tourSystem.service.serviceImpl;

import com.nju.tourSystem.entity.Activity;
import com.nju.tourSystem.mapper.ActivityMapper;
import com.nju.tourSystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Activity getActivityById(int id) {
        return activityMapper.getActivityById(id);
    }

    @Override
    public List<Activity> getAllActivity() {
        return activityMapper.getAllActivity();
    }


    @Override
    public List<Activity> searchActivity(String keyword) {
        return activityMapper.searchActivity(keyword);
    }

    @Override
    public List<Activity> getActivityByOrganizerId(String organizerId) {
        return activityMapper.getActivityByOrganizerId(organizerId);
    }

    @Override
    public List<Activity> getNewActivity(String date) {
//        System.out.println(date);
        return activityMapper.getNewActivity(date);
    }

    @Override
    public List<Activity> getOngoingActivity(String date) {
        return activityMapper.getOngoingActivity(date);
    }

    @Override
    public List<Activity> getFinishedActivity(String date) {
        return activityMapper.getFinishedActivity(date);
    }

    @Override
    public Boolean addAcitvity(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public Boolean updateAcitvity(Activity activity) {
        return activityMapper.update(activity);
    }
}
