package com.zhengbing.util.annotation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

/**
 * 自定义 json 过滤器
 */
public class CustomerJsonSerializer implements PropertyPreFilter {

    private Class< ? > clazz;

    private final Set< String > includes = new HashSet< String >();

    private final Set< String > excludes = new HashSet< String >();

    @Override
    public boolean apply( JSONSerializer serializer, Object source, String name ) {

        if ( source == null ) {
            return true;
        }

        if ( clazz != null && !clazz.isInstance( source ) ) {
            return true;
        }

        if ( this.excludes.contains( name ) ) {
            return false;
        }

        if ( includes.size() == 0 || includes.contains( name ) ) {
            return true;
        }
        return false;
    }

    public void filter( Class< ? > clazz, String include, String filter ) {
        if ( clazz == null ) {
            return;
        }

        this.clazz = clazz;

        // 处理包含的
        if ( StringUtils.hasLength( include ) ) {
            String[] inculdeArr = StringUtils.delimitedListToStringArray( include, "," );
            for ( String inculdeStr : inculdeArr ) {
                this.includes.add( inculdeStr );
            }
        }
        // 处理排除的
        if ( StringUtils.hasLength( filter ) ) {
            String[] filterArr = StringUtils.delimitedListToStringArray( filter, "," );
            for ( String filterStr : filterArr ) {
                this.excludes.add( filterStr );
            }
        }
    }

    public void filter( JSON json ) {
        this.filter( json.type(), json.include(), json.filter() );
    }

    public Class< ? > getClazz() {
        return clazz;
    }
}
