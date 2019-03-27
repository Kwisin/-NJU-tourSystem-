package com.nju.tourSystem.controller;

import com.nju.tourSystem.entity.JsonResponse;
import com.nju.tourSystem.entity.TourRecord;
import com.nju.tourSystem.service.TourRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("游记信息管理")
@RestController
@RequestMapping("/tourRecord/*")
public class TourRecordController {
    @Autowired
    TourRecordService tourRecordService;

    /**
     *
     * @author yqe
     */
    @ApiOperation("根据id查询游记")
    @RequestMapping(value = "/getArticleInfo/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getArticle(@PathVariable int id) {
        JsonResponse r = new JsonResponse();
        try {
            TourRecord tourRecord = tourRecordService.getTourRecordById(id);
            r.setData(tourRecord);
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
    @ApiOperation("根据用户查询其游记")
    @RequestMapping(value = "/getArticleByUser/{uid}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponse> getArticleByUser(@PathVariable String uid) {
        JsonResponse r = new JsonResponse();
        try {
            List<TourRecord> tourRecordList = tourRecordService.getTourRecordByUid(uid);
            r.setData(tourRecordList);
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
    @ApiOperation("发布游记")
    @RequestMapping(value = "/publishArticle", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> publishArticle(@RequestBody TourRecord tourRecord) {
        JsonResponse r = new JsonResponse();
        try {
            Boolean state = tourRecordService.addTourRecord(tourRecord);
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
    @ApiOperation("编辑游记信息")
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> updateActivity(@RequestBody TourRecord tourRecord) {
        JsonResponse r = new JsonResponse();
        try {
            Boolean state = tourRecordService.updateTourRecord(tourRecord);
            r.setData(state);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

}
