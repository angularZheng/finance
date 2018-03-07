package com.zhengbing.controller;

import com.zhengbing.common.pay.WXPayConfigImpl;
import com.zhengbing.entity.Product;
import com.zhengbing.entity.UserPayRecord;
import com.zhengbing.service.IUserPayRecordService;
import com.zhengbing.util.DateUtils;
import com.zhengbing.util.pay.WXPay;
import com.zhengbing.util.pay.WXPayConstants;
import com.zhengbing.util.pay.WXPayUtil;
import com.zhengbing.entity.Order;
import com.zhengbing.entity.User;
import com.zhengbing.service.IOrderService;
import com.zhengbing.service.IUserService;
import com.zhengbing.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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

    @RequestMapping( value = "pay/{orderId}" )
    public ResponseEntity wxpay( HttpServletRequest request, @PathVariable Integer orderId ) throws Exception {

        User user = ( User ) request.getSession().getAttribute( "user" );
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay( config );

        Order order = orderService.findById( orderId );
        System.out.println( order );
        System.out.println( order.getProduct() );
        System.out.println( order.getProduct().getProductName() + ":productName" );
        Map< String, String > data = new HashMap< String, String >();
        data.put( "body", order.getProduct().getProductName() );
        data.put( "openid", user.getOpenId() );
        data.put( "out_trade_no", order.getOrderNo() );
        data.put( "device_info", "WEB" );
        data.put( "fee_type", "CNY" );
        data.put( "total_fee", order.getAmount().multiply( new BigDecimal( 100 ) ).setScale( 0 ) + "" );
        data.put( "spbill_create_ip", request.getLocalAddr() );
        data.put( "notify_url", "http://financets.duapp.com/wxpay/notify" );

        data.put( "trade_type", "JSAPI" );  // 此处指定为公众号支付

        try {
            Map< String, String > resp = wxpay.unifiedOrder( data );
            System.out.println( resp + "           *********************" );
            if ( null != resp ) {
                Map< String, String > jsResult = new HashMap<>();
                if ( "SUCCESS".equals( resp.get( "return_code" ).toString() ) ) {
                    String timestamp = new Date().getTime() / 1000 + "";
                    jsResult.put( "timeStamp", timestamp );
                    String packages = "prepay_id=" + resp.get( "prepay_id" );
                    jsResult.put( "package", packages );
                    jsResult.put( "appId", config.getAppID() );
                    jsResult.put( "nonceStr", WXPayUtil.generateNonceStr() );
                    jsResult.put( "signType", WXPayConstants.HMACSHA256 );
                    String sign = WXPayUtil.generateSignature( jsResult, config.getKey(), WXPayConstants.SignType.HMACSHA256 );
                    jsResult.put( "sign", sign );
                    jsResult.put( "return_code", resp.get( "return_code" ) );
                    jsResult.put( "return_msg", resp.get( "return_msg" ) );

                }
                return ResponseEntity.ok( jsResult );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping( value = "notify", method = RequestMethod.POST )
    public void wechatPayNotify( HttpServletRequest request ,HttpServletResponse response) {
        try {
            Map< String, String > map = getCallbackParams( request );
            Map< String, String > callback = new HashMap<>();
            if ( map.get( "result_code" ).toString().equalsIgnoreCase( "SUCCESS" ) ) {
                String orderNo = map.get( "out_trade_no" );
                //这里写成功后的业务逻辑
                Order order = orderService.findByOrderNo( orderNo );

                Product product = order.getProduct();
                // 修改订单状态
                order.setStatus( Constants.ORDER_STATUS_PAYED );
                order.setUpdateTime( new Date() );
                // 修改用户角色
                User user = userService.findById( order.getUserId() );
                user.setRoleId( Constants.ROLE_VIP );


                // 修改用户会员有效期
                if ( null == user.getExpireDate() ) {
                    // 如果会员有效期为空，则从当前日期起有效期加上产品的有效期
                    user.setExpireDate( DateUtils.addMonth( new Date(), product.getExpireTime() ) );
                } else if ( user.getExpireDate().getTime() < DateUtils.now().getTime() ) {
                    user.setExpireDate( DateUtils.addMonth( new Date(), product.getExpireTime() ) );
                } else if ( user.getExpireDate().getTime() < DateUtils.now().getTime() ) {
                    user.setExpireDate( DateUtils.addMonth( user.getExpireDate(), product.getExpireTime() ) );
                }
                user.setUpdateBy( user.getNickname() );
                user.setUpdateTime( new Date() );
                // 添加用户支付记录
                UserPayRecord userPayRecord = new UserPayRecord();
                userPayRecord.setAmount( order.getAmount() );
                userPayRecord.setPayTime( new Date() );
                userPayRecord.setUserId( order.getUserId() );
                user.setCreateTime( new Date(  ) );
                userPayRecord = userPayRecordService.save( userPayRecord );

                if ( userPayRecord.getId() > 0 ) {
                    callback.put( "return_code", "success" );
                    callback.put( "return_msg", "支付成功" );
                } else {
                    callback.put( "return_code", "fail" );
                    callback.put( "return_msg", "支付失败" );
                }
            }
            response.getWriter().print( WXPayUtil.mapToXml( callback ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    /**
     * 获取请求参数
     *
     * @param @param  request
     * @param @return
     * @param @throws Exception
     * @return Map<String,String>
     * @throws
     * @Title: getCallbackParams
     * @Description: TODO
     */
    public Map< String, String > getCallbackParams( HttpServletRequest request )
            throws Exception {
        request.setCharacterEncoding( "UTF-8" );
        BufferedReader br = new BufferedReader( new InputStreamReader( ( ServletInputStream ) request.getInputStream() ) );
        String line = null;
        //sb为微信返回的xml
        StringBuilder sb = new StringBuilder();
        while ( ( line = br.readLine() ) != null ) {
            sb.append( line );
        }
        return WXPayUtil.xmlToMap( sb.toString() );
    }

}
