package com.nju.tourSystem.service.serviceImpl;

import com.nju.tourSystem.entity.TourRecord;
import com.nju.tourSystem.mapper.TourRecordMapper;
import com.nju.tourSystem.service.TourRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourRecordServiceImpl implements TourRecordService {
    @Autowired
    TourRecordMapper tourRecordMapper;
    @Override
    public boolean addTourRecord(TourRecord tourRecord) {
        return tourRecordMapper.insert(tourRecord);
    }

    @Override
    public boolean updateTourRecord(TourRecord tourRecord) {
        return tourRecordMapper.update(tourRecord);
    }

    @Override
    public TourRecord getTourRecordById(int id) {
        return tourRecordMapper.getTourRecordById(id);
    }

    @Override
    public List<TourRecord> getTourRecordByUid(String uid) {
        return tourRecordMapper.getTourRecordByUid(uid);
    }
}
