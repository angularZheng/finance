package com.zhengbing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhengbing.common.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengbing on 2017-10-19.
 */
@Data
@Entity
@Table(name = "fc_user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "user_name", length = 50)
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "password", length = 120)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    /**
     * 用户头像
     */
    @Column(name = "head_img_url", length = 255)
    private String headImgUrl;

    /**
     * 用户性别
     */
    @Column(name = "sex", length = 1)
    private Integer sex;

    /**
     * 用户邮箱
     */
    @Column(name = "email", length = 50)
    private String email;

    /**
     * 第三方 openid
     */
    @Column(name = "open_id", length = 32)
    private String openId;

    /**
     * 所属城市
     */
    @Column(name = "city", length = 32)
    private String city;

    /**
     * 所属省份
     */
    @Column(name = "province", length = 32)
    private String province;

    /**
     * 所属国家
     */
    @Column(name = "country", length = 50)
    private String country;

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false,updatable = false,insertable = false)
    private Role role;

    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 会员等级
     */
    @Column(name = "vip_level")
    private Integer vipLevel;


}

