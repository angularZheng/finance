package com.zhengbing.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhengbing on 2017/10/19.
 */
@Data
public class WxUserInfo implements Serializable {

    /**
     *
     */
    private String openid;

    private String nickname;

    private int sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private int subscribe;

    private String headimgurl;

    private Long subscribe_time;
}
