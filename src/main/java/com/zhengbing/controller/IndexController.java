package com.zhengbing.controller;

import com.zhengbing.entity.User;
import com.zhengbing.service.IUserService;
import com.zhengbing.util.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhengbing on 2017/10/19.
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping( value = "/" )
    public String index( HttpServletRequest request) {

        User user = userService.findById( 2 );
        request.getSession().setAttribute( "user",user );
        return "vip";
    }

}
