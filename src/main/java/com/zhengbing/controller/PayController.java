package com.zhengbing.controller;

import com.zhengbing.common.pay.MyConfig;
import com.zhengbing.common.pay.WXPay;
import com.zhengbing.common.pay.WXPayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by zhengbing on 2017/11/2.
 */
@Controller
@RequestMapping("wxpay")
public class PayController {

    @RequestMapping(value = "pay")
    public void wxpay(HttpServletRequest request)throws Exception {
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.y-create.cn/wxpay/callback");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "callback")
    public String wechatPayNotify(HttpServletRequest request) throws Exception {
        try {
            Map<String, String> map = getCallbackParams(request);
            Map<String,String> callback = new HashMap<>();
            if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                String orderId = map.get("out_trade_no");
                //这里写成功后的业务逻辑
                //orderService.updateConfirm(orderId);
                callback.put("return_code","");
                callback.put("return_msg","");
            }
            return WXPayUtil.mapToXml(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 获取请求参数
     * @Title: getCallbackParams
     * @Description: TODO
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Map<String,String>
     * @throws
     */
    public Map<String, String> getCallbackParams(HttpServletRequest request)
            throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        return WXPayUtil.xmlToMap(result);
    }


}
