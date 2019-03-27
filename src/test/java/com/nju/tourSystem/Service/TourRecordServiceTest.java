package com.nju.tourSystem.Service;

import com.nju.tourSystem.entity.TourRecord;
import com.nju.tourSystem.service.TourRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TourRecordServiceTest {
    @Autowired
    TourRecordService tourRecordService;

    /**
     * 测试发布游记
     */
    @Test
    public void addTourRecordTest() throws Exception {
        String userId = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        TourRecord tourRecord = new TourRecord();
        tourRecord.setUid(userId);
        tourRecord.setTitle("游记标题");
        tourRecord.setContent("这是一篇游记的内容");
        tourRecord.setPublishTime("2019-02-03");
        tourRecord.setReadNum(1);
        Assert.assertTrue(tourRecordService.addTourRecord(tourRecord));
    }

    /**
     * 测试根据ID查找游记
     */
    @Test
    public void getTourRecordByIdTest() throws Exception {
        TourRecord tourRecord = tourRecordService.getTourRecordById(1);
    }

    /**
     * 测试根据ID查找游记
     */
    @Test
    public void getTourRecordByUidTest() throws Exception {
        String uid = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        List<TourRecord> tourRecordList = tourRecordService.getTourRecordByUid(uid);
    }

    /**
     * 测试更新游记
     */
    @Test
    public void updateTourRecordTest() throws Exception {
        TourRecord tourRecord = tourRecordService.getTourRecordById(1);
        tourRecord.setReadNum(100);
        Assert.assertTrue(tourRecordService.updateTourRecord(tourRecord));
    }


}
