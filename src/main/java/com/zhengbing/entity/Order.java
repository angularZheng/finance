package com.zhengbing.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengbing on 2017/11/2.
 */
@Data
@Entity
@Table(name = "fc_order")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private Integer id;

    @Column(name="order_no",length=50)
    private String orderNo;

    @Column(name = "amount",columnDefinition = "decimal(14,2)")
    private String amount;
}
