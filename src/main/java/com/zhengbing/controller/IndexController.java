package com.zhengbing.controller;

import com.zhengbing.util.MessageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhengbing on 2017/10/19.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index( Model model){
        MessageBean msg = new MessageBean("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "success";
    }

}
