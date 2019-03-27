package com.nju.tourSystem.service.serviceImpl;

import com.nju.tourSystem.entity.Participant;
import com.nju.tourSystem.mapper.ParticipantMapper;
import com.nju.tourSystem.service.ParticipantService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    ParticipantMapper participantMapper;
    @Override
    public boolean addParticipant(Participant participant) {
        return participantMapper.insert(participant);
    }

    @Override
    public boolean updateParticipant(Participant participant) {
        return participantMapper.update(participant);
    }

    @Override
    public Participant getParticipantById(int id) {
        return participantMapper.getParticipantById(id);
    }

    @Override
    public List<Participant> getRefusedListByUid(String uid) {
        return participantMapper.getRefusedListByUid(uid);
    }

    @Override
    public List<Participant> getActivityListByUid(String uid) {
        return participantMapper.getActivityListByUid(uid);
    }

    @Override
    public List<Participant> getApplicationListByUid(String uid) {
        return participantMapper.getApplicationListByUid(uid);
    }

    @Override
    public List<Participant> getRefusedList(int aid) {
        return participantMapper.getRefusedList(aid);
    }

    @Override
    public List<Participant> getParticipantList(int aid) {
        return participantMapper.getParticipantList(aid);
    }

    @Override
    public List<Participant> getApplicationList(int aid) {
        return participantMapper.getApplicationList(aid);
    }

    @Override
    public Boolean delete(int id) {
        return participantMapper.delete(id);
    }

    @Override
    public List<Integer> searchHotActivity() {
        return participantMapper.searchHotActivity();
    }
}
