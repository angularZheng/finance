package com.zhengbing.entity;

import com.zhengbing.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhengbing on 2017-10-31.
 */

@Data
@Entity
@Table( name = "fc_role" )
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @Column( name = "role_name", length = 50 )
    private String roleName;

    /**
     * 角色描述
     */
    @Column( name = "description", length = 100 )
    private String description;

}
