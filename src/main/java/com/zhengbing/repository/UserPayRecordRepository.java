package com.zhengbing.repository;

import com.zhengbing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhengbing on 2017/11/29.
 */
public interface UserPayRecordRepository extends JpaRepository<Order,Integer> {
}
