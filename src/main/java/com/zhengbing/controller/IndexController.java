package com.zhengbing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhengbing on 2017/10/17.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public  String index(){
        return "zhengbing";
    }
}
