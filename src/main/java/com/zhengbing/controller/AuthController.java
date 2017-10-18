package com.zhengbing.controller;

import com.zhengbing.util.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by zhengbing on 2017/10/18.
 */
@Controller
public class AuthController {

    @RequestMapping("wxLogin")
    public void wxLogin(HttpServletResponse response) throws IOException{
        String backUrl = "http://h187n06299.iok.la/callback";
        System.out.println(backUrl);
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid="+ AuthUtil.APPID
                + "&redirect_uri="+ backUrl
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect ";
        System.out.println(url);
        response.sendRedirect(url);
    }

}
