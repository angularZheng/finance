package com.zhengbing.controller;

import com.zhengbing.entity.User;
import com.zhengbing.entity.UserPayRecord;
import com.zhengbing.service.IUserPayRecordService;
import com.zhengbing.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by zhengbing on 2017/12/4.
 */
@Controller
public class UserPayRecordController {

    @Autowired
    private IUserPayRecordService userPayRecordService;

    @Autowired
    private IUserService userService;

    @RequestMapping("admin/payrecords")
    public String findPage(@PageableDefault( sort = { "id" }, direction = Sort.Direction.DESC)
                                       Pageable pageable, HttpServletRequest request, Model model){

        Page<User> page = userService.findPageable(pageable);
        model.addAttribute("page",page);
        return "admin/payrecord_list";
    }
}
