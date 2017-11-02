package com.zhengbing.repository;

import com.zhengbing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhengbing on 2017-10-31.
 */
public interface RoleRepository  extends JpaRepository<Role,Integer> {

}
