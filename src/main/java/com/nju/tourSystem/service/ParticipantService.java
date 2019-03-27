package com.nju.tourSystem.service;

import com.nju.tourSystem.entity.Participant;

import java.util.List;

public interface ParticipantService {
    boolean addParticipant(Participant participant);
    boolean updateParticipant(Participant participant);
    Participant getParticipantById(int id);
    List<Participant> getRefusedListByUid(String uid);
    List<Participant> getActivityListByUid(String uid);
    List<Participant> getApplicationListByUid(String uid);
    List<Participant> getRefusedList(int aid);
    List<Participant> getParticipantList(int aid);
    List<Participant> getApplicationList(int aid);
    Boolean delete(int id);
    //返回参与人数多的活动
    List<Integer> searchHotActivity();
}
