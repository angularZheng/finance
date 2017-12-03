package com.zhengbing.service.impl;

import com.zhengbing.entity.Order;
import com.zhengbing.repository.OrderRepository;
import com.zhengbing.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/11/2.
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findByOrderNo(String orderNo) {
        return orderRepository.findByOrderNo(orderNo);
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findOne(id);
    }
}
