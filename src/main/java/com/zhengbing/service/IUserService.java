package com.zhengbing.service;

import com.zhengbing.entity.User;

/**
 * Created by zhengbing on 2017/10/19.
 */
public interface IUserService {

    User findUserByName( String name) throws Exception;
}
