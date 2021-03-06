package com.zhengbing.service;

import com.zhengbing.entity.Order;

/**
 * Created by zhengbing on 2017/11/2.
 */
public interface IOrderService {

    Order save(Order order);

    Order update(Order order);

    Order findByOrderNo(String orderNo);

    Order findById(Integer id);
}
