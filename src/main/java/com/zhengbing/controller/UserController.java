package com.zhengbing.controller;

//import com.zhengbing.entity.UserInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhengbing on 2017-10-19.
 */
@Controller
public class UserController {


    @RequestMapping(value = "/user/index")
    public String index(){
        return "index";
    }
}
