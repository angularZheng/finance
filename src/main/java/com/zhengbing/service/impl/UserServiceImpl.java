package com.zhengbing.service.impl;

import com.zhengbing.entity.User;
import com.zhengbing.repository.UserRepository;
import com.zhengbing.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/10/19.
 */
@Service( "userService" )
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User save( User user ) {
        return userRepository.save( user );
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername( username );
    }

    @Override
    public User update(User user){
        return userRepository.save( user );
    }

    @Override
    public User findByOpenId( String openId ) {
        return userRepository.findByOpenId( openId );
    }

}
