package com.nju.tourSystem.service;

import com.nju.tourSystem.entity.Friendship;

import java.util.List;

public interface FriendshipService {
    boolean addFriend(Friendship friendship);
    boolean updateFriend(Friendship friendship);
    Friendship getFriendshipById(int id);
    List<Friendship> getFriendshipListByUid(String uid);
    List<Friendship> getApplicationListByUid(String uid);
    Boolean delete(int id);
}
