package com.zhengbing.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhengbing on 2017-11-03.
 */
public class DateUtils {

    /**
     * 指定日期添加指定月
     *
     * @param date
     * @param num
     *
     * @return 计算之后的日期
     */
    public static Date addMonth( Date date, int num ) {
        GregorianCalendar now = new GregorianCalendar();
        now.setTime( date );
        now.add( Calendar.MONTH, num );
        date = now.getTime();
        return date;
    }

    public static void main( String[] args ) {
        System.out.println( DateUtils.addMonth( new Date(),1 ) );
    }
}
