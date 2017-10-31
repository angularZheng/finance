package com.zhengbing.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengbing on 2017-10-31.
 */

@Data
@Entity
@Table(name = "fc_role")
public class Role{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private Integer id;

    @Column(name="name",length=100)
    private String name;//角色名称

}
