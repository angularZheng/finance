package com.zhengbing.service;

import com.zhengbing.entity.User;

/**
 * Created by zhengbing on 2017/10/19.
 */
public interface IUserService {

    /**
     *
     * 添加用户
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     *
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername( String username);

    /**
     *
     * 根据 微信openid 查找用户
     *
     * @param openId
     * @return
     */
    User findByOpenId(String openId);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    User update(User user);
}
