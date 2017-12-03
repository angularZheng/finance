package com.zhengbing.repository;

import com.zhengbing.entity.Order;
import com.zhengbing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhengbing on 2017/12/3.
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
