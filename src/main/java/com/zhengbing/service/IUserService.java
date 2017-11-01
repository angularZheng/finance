package com.zhengbing.service;

import com.zhengbing.entity.User;

/**
 * Created by zhengbing on 2017/10/19.
 */
public interface IUserService {

    User findByUsername( String username);

    User update(User user);
}
