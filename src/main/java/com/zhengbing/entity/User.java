package com.zhengbing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "user_name", length = 120)
    private String username; //用户名

    @Column(name = "head_icon",length = 120)
    private String headIcon;

    @Column(name = "email", length = 50)
    private String email;//用户邮箱

    @Column(name = "password", length = 120)
    private String password;//用户密码

    @Column(name = "salt")
    private String salt;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob", length = 10)
    private Date dob;//时间

    @Column(name = "open_id", length = 32)
    private String openId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "vip_level ")
    private UserVipLevel vipLevel;

}

