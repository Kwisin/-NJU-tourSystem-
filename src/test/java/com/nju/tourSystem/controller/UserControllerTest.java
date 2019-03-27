package com.nju.tourSystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.tourSystem.entity.User;
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
 * @author yqe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    /**
     * 测试根据ID获取用户信息
     */
    @Test
    public void getUserTest() throws Exception {
        String userId = "odTDj5BIrPqZBG2qkh6DpXxKLlng";
        mvc.perform(MockMvcRequestBuilders.get("/user/userinfo/"+userId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试获取用户列表
     */
    @Test
    public void getUserListTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/userlist")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    /**
     * 测试更新用户
     */
//    @Test
    public void updateUserTest() throws Exception {
        String json="{\"id\":\"odTDj5BIrPqZBG2qkh6DpXxKLlng\",\"columnid\":\"年龄\",\"value\":\"25\"}";
        mvc.perform(MockMvcRequestBuilders.post("/user/updateUser")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .contentType("application/json; charset=UTF-8")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    /**
     * 测试查找用户
     */
    @Test
    public void searchUserTest() throws Exception {
        String keyword = "剑";
        mvc.perform(MockMvcRequestBuilders.get("/user/searchUser/"+keyword)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}


