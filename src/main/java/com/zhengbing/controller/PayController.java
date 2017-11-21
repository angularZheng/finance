package com.zhengbing.controller;

import com.zhengbing.common.pay.MyConfig;
import com.zhengbing.common.pay.WXPay;
import com.zhengbing.common.pay.WXPayUtil;
import com.zhengbing.entity.Order;
import com.zhengbing.service.IOrderService;
import com.zhengbing.util.web.ParameterUtil;
import com.zhengbing.util.StringUtil;
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

    @RequestMapping( value = "pay" )
    public void wxpay( HttpServletRequest request) throws Exception {

        // 生成应用内订单
        Order order = new Order();
        order.setUserId( ParameterUtil.getIntParameter( request,"userId" ) );
        order.setOrderNo( StringUtil.ganerateOrderNo() );
        order.setAmount( ParameterUtil.getDoubleParameter( request,"amount" ) );
        order.setDescription( ParameterUtil.getParameter( request,"description" ));
        order = orderService.save( order );

        // 统一下单
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay( config );

        Map< String, String > data = new HashMap< String, String >();
        data.put( "body", "会员充值" );
        data.put( "out_trade_no", order.getOrderNo() );
        data.put( "device_info", "" );
        data.put( "fee_type", "CNY" );
        data.put( "total_fee", order.getAmount()+"" );
        data.put( "spbill_create_ip", request.getLocalAddr() );
        data.put( "notify_url", "http://financetx.duapp.com/wxpay/callback" );
        data.put( "trade_type", "NATIVE" );  // 此处指定为扫码支付
        data.put( "product_id", "12" );

        try {
            Map< String, String > resp = wxpay.unifiedOrder( data );
            System.out.println( resp );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @RequestMapping( value = "callback" )
    public String wechatPayNotify( HttpServletRequest request ) {
        try {
            Map< String, String > map = getCallbackParams( request );
            Map< String, String > callback = new HashMap<>();
            if ( map.get( "result_code" ).toString().equalsIgnoreCase( "SUCCESS" ) ) {
                String orderNo = map.get( "out_trade_no" );
                //这里写成功后的业务逻辑
                // 修改订单状态
                // 修改用户角色
                //orderService.updateConfirm(orderId);
                callback.put( "return_code", "" );
                callback.put( "return_msg", "" );
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
