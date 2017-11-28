package com.zhengbing.controller;

import com.zhengbing.entity.Role;
import com.zhengbing.entity.User;
import com.zhengbing.service.IRoleService;
import com.zhengbing.service.IUserService;
import com.zhengbing.util.AuthUtil;
import com.zhengbing.util.Constants;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 微信授权认证controller
 * <p>
 * Created by zhengbing on 2017/10/18.
 */
@Controller
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
    /**
     * 微信登陆授权
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("wxLogin")
    public void wxLogin(HttpServletResponse response) throws IOException {
        String backUrl = "http://afa46dbf.ngrok.io/callback";
        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        requestUrl = requestUrl.replace("APPID", AuthUtil.APPID);
        requestUrl = requestUrl.replace("REDIRECT_URI", URLEncoder.encode(backUrl, "UTF-8"));
        requestUrl = requestUrl.replace("SCOPE", "snsapi_userinfo");
        response.sendRedirect(requestUrl);
    }

    /**
     * 微信授权登陆回调函数
     *
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "callback")
    public String callBack(HttpServletRequest request, Model model) throws IOException {

        String code = request.getParameter("code");
        // 根据微信回调返回的code 获取 openid 和access_token
        String backUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        backUrl = backUrl.replace("APPID", AuthUtil.APPID);
        backUrl = backUrl.replace("SECRET", AuthUtil.APPSECRET);
        backUrl = backUrl.replace("CODE", code);
        JSONObject jsonObject = AuthUtil.doGetJson(backUrl);

        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        infoUrl = infoUrl.replace("ACCESS_TOKEN", token);
        infoUrl = infoUrl.replace("OPENID", openid);
        JSONObject userinfo = AuthUtil.doGetJson(infoUrl);
        User user = null;
        if (!StringUtils.isEmpty(openid)) {
            if (null == userService.findByOpenId(openid)) {
                user = new User();
                user.setCity(userinfo.getString("city"));
                user.setProvince(userinfo.getString("province"));
                user.setCountry(userinfo.getString("country"));
                user.setSex(userinfo.getInt("sex"));
                user.setOpenId(userinfo.getString("openid"));
                user.setNickname(userinfo.getString("nickname"));
//              user.setLanguage(userinfo.getString("language" ));
                user.setHeadImgUrl(userinfo.getString("headimgurl"));
                user.setVipLevel(Constants.VIP_NORMAL);
                user.setRoleId(Constants.ROLE_VIP);

                user = userService.save(user);
            } else {
                user = userService.findByOpenId(openid);
            }
        }
        model.addAttribute("user", user);
        if (user.getVipLevel() == Constants.VIP_FEE) {
            return "vip";
        } else {
            return "normal";
        }
    }
}
