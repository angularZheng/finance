package com.zhengbing.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zhengbing on 2017-11-01.
 */

@Data
@Entity
@Table(name = "fc_user_vip_level")
public class UserVipLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "level_name",length = 5)
    private String levelName;

    @NotNull
    @Column(name = "min_amount",columnDefinition = "decimal(14,2)")
    private Integer minAmount;

    @NotNull
    @Column(name = "max_amount",columnDefinition = "decimal(14,2)")
    private Integer maxAmount;
}
