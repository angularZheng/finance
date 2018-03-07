package com.zhengbing.service.impl;

import com.zhengbing.entity.Product;
import com.zhengbing.repository.ProductRepository;
import com.zhengbing.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengbing on 2017/12/3.
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Integer id) {
        return productRepository.findOne( id );
    }
}
