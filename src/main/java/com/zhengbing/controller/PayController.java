package com.zhengbing.controller;

import com.zhengbing.common.pay.WXPayConfigImpl;
import com.zhengbing.entity.UserPayRecord;
import com.zhengbing.service.IUserPayRecord;
import com.zhengbing.service.IUserPayRecordService;
import com.zhengbing.util.pay.WXPay;
import com.zhengbing.util.pay.WXPayUtil;
import com.zhengbing.entity.Order;
import com.zhengbing.entity.User;
import com.zhengbing.service.IOrderService;
import com.zhengbing.service.IUserService;
import com.zhengbing.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by zhengbing on 2017/11/2.
 */
@Controller
@RequestMapping( "wxpay" )
public class PayController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserPayRecordService userPayRecordService;

    private WXPay wxpay;
    private WXPayConfigImpl config;

    @RequestMapping( value = "pay" )
    public void wxpay( HttpServletRequest request,Integer orderId) throws Exception {

        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);

        Order order = orderService.findById(orderId);

        Map< String, String > data = new HashMap< String, String >();
        data.put( "body", "会员充值" );
        data.put( "out_trade_no", order.getOrderNo() );
        data.put( "device_info", "" );
        data.put( "fee_type", "CNY" );
        data.put( "total_fee", order.getAmount()+"" );
        data.put( "spbill_create_ip", request.getLocalAddr() );
        data.put( "notify_url", "http://financetx.duapp.com/wxpay/notify" );
        data.put( "trade_type", "JSAPI" );  // 此处指定为公众号支付
        data.put( "product_id", "12" );

        try {
            Map< String, String > resp = wxpay.unifiedOrder( data );
            System.out.println( resp );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @RequestMapping( value = "notify" )
    public String wechatPayNotify( HttpServletRequest request ) {
        try {
            Map< String, String > map = getCallbackParams( request );
            Map< String, String > callback = new HashMap<>();
            if ( map.get( "result_code" ).toString().equalsIgnoreCase( "SUCCESS" ) ) {
                String orderNo = map.get( "out_trade_no" );
                //这里写成功后的业务逻辑
                Order order = orderService.findByOrderNo(orderNo);
                // 修改订单状态
                order.setStatus(Constants.ORDER_STATUS_PAYED);
                // 修改用户角色
                User user = userService.findById(order.getUserId());
                user.setVipLevel(Constants.VIP_FEE);

                // 添加用户支付记录
                UserPayRecord userPayRecord = new UserPayRecord();
                userPayRecord.setAmount(order.getAmount());
                userPayRecord.setPayTime(new Date());
                userPayRecord.setUserId(order.getUserId());

                userPayRecord = userPayRecordService.save(userPayRecord);

                if (userPayRecord.getId()>0) {
                    callback.put("return_code", "success");
                    callback.put("return_msg", "支付成功");
                }else{
                    callback.put("return_code", "fail");
                    callback.put("return_msg", "支付失败");
                }
            }
            return WXPayUtil.mapToXml( callback );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取请求参数
     *
     * @param @param  request
     * @param @return
     * @param @throws Exception
     *
     * @return Map<String,String>
     *
     * @throws
     * @Title: getCallbackParams
     * @Description: TODO
     */
    public Map< String, String > getCallbackParams( HttpServletRequest request )
            throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ( ( len = inStream.read( buffer ) ) != -1 ) {
            outSteam.write( buffer, 0, len );
        }
        System.out.println( "~~~~~~~~~~~~~~~~付款成功~~~~~~~~~" );
        outSteam.close();
        inStream.close();
        String result = new String( outSteam.toByteArray(), "utf-8" );
        return WXPayUtil.xmlToMap( result );
    }

}
