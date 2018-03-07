package com.zhengbing.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhengbing on 2018-02-07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    /**
     * 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化
     */
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void order() throws Exception {
        MvcResult result = mvc.perform(post("/order/2").contentType( MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
//                .andExpect(content().contentType(MediaType.))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        System.out.println(result.getResponse().getContentAsString());
    }

}