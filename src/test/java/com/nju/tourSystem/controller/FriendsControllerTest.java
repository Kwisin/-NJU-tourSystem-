package com.nju.tourSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author yqe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FriendsControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 测试添加好友
     */
    @Test
    public void addFriendsTest() throws Exception {
        String uid = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        String fid = "odTDj5GXFvXz01PdRj0NMHQhBqBc";
        mvc.perform(MockMvcRequestBuilders.get("/friends/addFriends/"+uid+","+fid)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试同意添加好友
     */
//    @Test
    public void friendsAgreementTest() throws Exception {
        int id = 1;
        mvc.perform(MockMvcRequestBuilders.get("/friends/friendsAgreement/"+id)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试获取好友列表
     */
    @Test
    public void friendsListTest() throws Exception {
        String uid = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        mvc.perform(MockMvcRequestBuilders.get("/friends/friendsList/"+uid)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试获取好友申请列表
     */
    @Test
    public void applicationListTest() throws Exception {
        String uid = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        mvc.perform(MockMvcRequestBuilders.get("/friends/applicationList/"+uid)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
