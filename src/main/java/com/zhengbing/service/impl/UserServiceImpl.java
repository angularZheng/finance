package com.zhengbing.service.impl;

import com.zhengbing.entity.User;
import com.zhengbing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by zhengbing on 2017/10/19.
 */
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String name ) throws UsernameNotFoundException {
        User user = userRepository.findByName( name );

        if (user == null) {
            throw new UsernameNotFoundException( "用户名不存在" );
        }
        System.out.println("s:"+ name );
        System.out.println("username:"+user.getName()+";password:"+user.getPassword());
        return user;
    }
}
