package com.zhengbing.service.impl;

import com.zhengbing.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zhengbing on 2018-02-07.
 */
@RunWith( SpringRunner.class )
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private IProductService productService;

    @Test
    public void findById() throws Exception {
        System.out.println(productService.findById( 1 ));
    }

}