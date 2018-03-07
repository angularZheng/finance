package com.zhengbing.common;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhengbing on 2017/10/19.
 */
@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Integer id;

    @Column( name = "status")
    private Integer status;

    @Column( name = "create_by", length = 50 )
    private String createBy;

    @Temporal( TemporalType.DATE )
    @Column( name = "create_time", length = 10 )
    private Date createTime;//时间

    @Column( name = "update_by", length = 50 )
    private String updateBy;

    @Temporal( TemporalType.DATE )
    @Column( name = "update_time", length = 10 )
    private Date updateTime;//时间
}
