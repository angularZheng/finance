package com.zhengbing.repository;

import com.zhengbing.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by zhengbing on 2018-02-07.
 */
@RunWith( SpringRunner.class )
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByOne(){
        Product product = productRepository.findOne( 1 );
        System.out.println(product.toString());
        System.out.println(product.getProductName());
    }

}