package com.zhengbing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhengbing on 2017/12/3.
 */
@Controller
public class PageController {

    @RequestMapping("/recommand")
    public String recommand(HttpServletRequest request){
        return "recommand";
    }

    @RequestMapping("/report")
    public String report(HttpServletRequest request){
        return "report";
    }
}
