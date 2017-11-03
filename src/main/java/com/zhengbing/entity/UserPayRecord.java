package com.zhengbing.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhengbing on 2017-11-01.
 */
@Data
@Entity
@Table( name = "fc_user_pay_record" )
public class UserPayRecord {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Integer id;

    @NotNull
    @Column( name = "user_id", length = 11 )
    private Integer userId;

    @Column( name = "amount", columnDefinition = "decimal(14,2)" )
    private BigDecimal amount;

    @NotNull
    @Column( name = "status", length = 1 )
    private int status;

    @Temporal( TemporalType.TIME )
    @Column( name = "pay_time", length = 18 )
    private Date payTime;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user_id", insertable = false, updatable = false )
    private User user;
}
