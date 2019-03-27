package com.nju.tourSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.tourSystem.entity.TourRecord;
import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.TourRecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author xjc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TourRecordControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private TourRecordService tourRecordService;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    /**
     * 测试根据ID查询游记
     */
    @Test
    public void getArticleTest() throws Exception {
        int articleId = 1;
        mvc.perform(MockMvcRequestBuilders.get("/tourRecord/getArticleInfo/"+articleId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试发布游记
     */
    @Test
    public void publishArticleTest() throws Exception {
        TourRecord tourRecord = new TourRecord();
        tourRecord.setContent("游记内容");
        tourRecord.setPublishTime("2018-01-01");
        tourRecord.setReadNum(1);
        tourRecord.setTitle("标题");
        tourRecord.setUid("odTDj5BIrPqZBG2qkh6DpXxKLlng");
        mvc.perform(MockMvcRequestBuilders.post("/tourRecord/publishArticle")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(tourRecord))
                .contentType("application/json; charset=UTF-8")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试根据用户查询其游记
     */
    @Test
    public void getArticleByUserTest() throws Exception {
        String userId = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        mvc.perform(MockMvcRequestBuilders.get("/tourRecord/getArticleByUser/"+userId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试编辑游记信息
     */
    @Test
    public void updateActivityTest() throws Exception {
        TourRecord tourRecord = tourRecordService.getTourRecordByUid("odTDj5BIrPqZBG2qkh6DpXxKLlng").get(0);
        tourRecord.setContent("内容测试新测试");
//        String json="{\"id\":\"1\",\"uid\":\"1\",\"title\":\"标题\",\"content\":\"内容\",\"publishTime\":\"2018-01-01\",\"readNum\":\"1\"";
        mvc.perform(MockMvcRequestBuilders.post("/tourRecord/updateArticle")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(tourRecord))
                .contentType("application/json; charset=UTF-8")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
