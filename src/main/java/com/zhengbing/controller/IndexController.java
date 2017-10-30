package com.zhengbing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhengbing on 2017/10/19.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "nomal";
    }

}
