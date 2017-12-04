package com.zhengbing.entity;

import com.zhengbing.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by zhengbing on 2017/12/3.
 */
@Data
@Entity
@Table(name = "fc_product")
public class Product extends BaseEntity{

    @Column(name = "product_name",length = 50,unique = true)
    private String productName;

    @Column(name = "price", columnDefinition = "decimal(14,2)" )
    private BigDecimal price;

    @Column(name = "expire_time",length = 2)
    private Integer expireTime;
}
