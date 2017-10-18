package com.zhengbing.controller;

import com.zhengbing.util.AuthUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhengbing on 2017/10/18.
 */
@Controller
public class CallBackController {


    @RequestMapping(value = "callback")
    public String callBack( HttpServletRequest request) throws IOException{
        String code = request.getParameter( "code" );
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=" + AuthUtil.APPID
                + "&secret=" +AuthUtil.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = " https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject userinfo = AuthUtil.doGetJson(infoUrl);
        System.out.println(userinfo);
        return "zhengbing";
    }

}
