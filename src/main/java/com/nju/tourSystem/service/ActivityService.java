package com.nju.tourSystem.service;

import com.nju.tourSystem.entity.Activity;

import java.util.List;

public interface ActivityService {
    Activity getActivityById(int id);
    List<Activity> getAllActivity();
    List<Activity> searchActivity(String keyword);
    List<Activity> getActivityByOrganizerId(String organizerId);
    List<Activity> getNewActivity(String date);
    List<Activity> getOngoingActivity(String date);
    List<Activity> getFinishedActivity(String date);
    Boolean addAcitvity(Activity activity);
    Boolean updateAcitvity(Activity activity);
}

