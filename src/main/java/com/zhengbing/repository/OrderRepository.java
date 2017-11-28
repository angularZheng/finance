package com.zhengbing.repository;

import com.zhengbing.entity.Order;
import com.zhengbing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhengbing on 2017/11/2.
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findByOrderNo(String orderNo);
}
