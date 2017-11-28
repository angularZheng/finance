package com.zhengbing.service;

import com.zhengbing.entity.Role;
import com.zhengbing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/11/28.
 */
public interface IRoleService {

    Role findRoleById(Integer id);
}
