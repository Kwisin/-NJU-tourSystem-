package com.nju.tourSystem.service.serviceImpl;

import com.nju.tourSystem.entity.Friendship;
import com.nju.tourSystem.mapper.FriendshipMapper;
import com.nju.tourSystem.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    FriendshipMapper friendshipMapper;
    @Override
    public boolean addFriend(Friendship friendship) {
        return friendshipMapper.insert(friendship);
    }

    @Override
    public boolean updateFriend(Friendship friendship) {
        return friendshipMapper.update(friendship);
    }

    @Override
    public Friendship getFriendshipById(int id) {
        return friendshipMapper.getFriendshipById(id);
    }

    @Override
    public List<Friendship> getFriendshipListByUid(String uid) {
        return friendshipMapper.getFriendshipListByUid(uid);
    }

    @Override
    public List<Friendship> getApplicationListByUid(String uid) {
        return friendshipMapper.getApplicationListByUid(uid);
    }

    @Override
    public Boolean delete(int id) {
        return friendshipMapper.delete(id);
    }
}
