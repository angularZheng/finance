package com.zhengbing.repository;

import com.zhengbing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhengbing on 2017-10-31.
 */
public interface UserRepository extends JpaRepository<User,Integer>{

     User findByUsername( String name );
}
