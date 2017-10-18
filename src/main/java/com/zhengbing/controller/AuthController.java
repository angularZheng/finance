package com.zhengbing.controller;

import com.zhengbing.util.AuthUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 微信授权认证controller
 *
 * Created by zhengbing on 2017/10/18.
 */
@Controller
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("wxLogin")
    public void wxLogin(HttpServletResponse response) throws IOException{
        String backUrl = "http://8d928ca5.ngrok.io/callback";
        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        requestUrl = requestUrl.replace("APPID",AuthUtil.APPID);
        requestUrl = requestUrl.replace("REDIRECT_URI",URLEncoder.encode(backUrl,"UTF-8"));
        requestUrl = requestUrl.replace("SCOPE","snsapi_userinfo");
        response.sendRedirect(requestUrl);
    }

    @RequestMapping(value = "callback")
    public String callBack( HttpServletRequest request) throws IOException{

        String code = request.getParameter( "code" );
        System.out.println(code);
        String backUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        backUrl = backUrl.replace("APPID" , AuthUtil.APPID);
        backUrl = backUrl.replace("SECRET" , AuthUtil.APPSECRET);
        backUrl = backUrl.replace("CODE", code);
        JSONObject jsonObject = AuthUtil.doGetJson(backUrl);

        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        infoUrl = infoUrl.replace("ACCESS_TOKEN",token);
        infoUrl = infoUrl.replace("OPENID",openid);
//        JSONObject userinfo = AuthUtil.doGetJson(infoUrl);

//        UserInfo userInfo = new UserInfo();
//        userInfo

        return "zhengbing";
    }

}
