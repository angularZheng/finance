package com.zhengbing.common.security;

import com.zhengbing.entity.User;
import com.zhengbing.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by zhengbing on 2017-11-01.
 */
@Component
public class FinanceUserDetailsService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername( String userName ) throws UsernameNotFoundException {

        //User对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用User中的name作为用户名:
        User user = userService.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        // SecurityUser实现UserDetails并将SysUser的name映射为username
        SecurityUser seu = new SecurityUser(user);
        return  seu;
    }
}
