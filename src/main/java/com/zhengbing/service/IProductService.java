package com.zhengbing.service;

import com.zhengbing.entity.Product;

/**
 * Created by zhengbing on 2017/12/3.
 */
public interface IProductService {

    Product findById(Integer id);
}
