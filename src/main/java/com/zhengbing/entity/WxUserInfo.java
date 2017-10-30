package com.zhengbing.entity;

import java.io.Serializable;

/**
 * Created by zhengbing on 2017/10/19.
 */
public class WxUserInfo implements Serializable{


    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
