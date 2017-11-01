package com.zhengbing.common.security;

import org.springframework.context.ApplicationContext;

/**
 * Created by zhengbing on 2017-11-01.
 */
public class Appctx {

        public static ApplicationContext ctx=null;

        public static Object getObject(String string){
            return ctx.getBean(string);
    }
}
