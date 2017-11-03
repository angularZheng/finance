package com.zhengbing.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 json 过滤注解
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
@Repeatable( JSONS.class )
public @interface JSON {

    Class< ? > type() default Class.class;

    String include() default "";

    String filter() default "";

    boolean map() default false;

    String mapType() default "";
}
