package com.zhengbing.entity;

import com.zhengbing.common.BaseEntity;
import lombok.Data;
import org.hamcrest.Description;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by zhengbing on 2017/11/2.
 */
@Data
@Entity
@Table( name = "fc_order" )
public class Order extends BaseEntity {

    @Column( name = "user_id", length = 11, nullable = false )
    private Integer userId;

    @Column( name = "order_no", length = 50 )
    private String orderNo;

    @Column( name = "amount", columnDefinition = "decimal(14,2)" )
    private double amount;

    @Column( name = "description", length = 100 )
    private String description;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user_id", insertable = false, updatable = false )
    private User user;


}
