package com.zhengbing.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhengbing on 2017/11/2.
 */
public class StringUtil extends StringUtils{

    public static String ganerateOrderNo() {
        StringBuffer orderNo = new StringBuffer();
        orderNo.append( DateFormatUtils.format( new Date(), "yyyyMMddHHmmss" ) );
        orderNo.append( ( int ) ( Math.random() * ( 9999 - 1000 + 1 ) + 1000 ) );
        return orderNo.toString();
    }

}
