package com.zhengbing.service.impl;

import com.zhengbing.entity.Role;
import com.zhengbing.repository.RoleRepository;
import com.zhengbing.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/11/28.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleById(Integer id) {
        return roleRepository.findOne(id);
    }
}
