package com.zhengbing.util.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 自定义 mvc json处理器. 处理自定义注解
 */
public class JsonReturnHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType( MethodParameter returnType ) {
        return returnType.getMethodAnnotation( JSON.class ) != null || returnType.getMethodAnnotation( JSONS.class ) != null;
    }

    @Override
    public void handleReturnValue( Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                   NativeWebRequest webRequest ) throws Exception {

        // 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
        mavContainer.setRequestHandled( true );

        // 获得注解并执行filter方法 最后返回
        HttpServletResponse response = webRequest.getNativeResponse( HttpServletResponse.class );
        Annotation[] annos = returnType.getMethodAnnotations();
        CustomerJsonSerializer[] customerJsonFilters = null;
        for ( Annotation anno : annos ) {
            if ( anno instanceof JSON ) {
                customerJsonFilters = new CustomerJsonSerializer[1];
                CustomerJsonSerializer filter = new CustomerJsonSerializer();
                filter.filter( ( JSON ) anno );
                customerJsonFilters[0] = filter;
            } else if ( anno instanceof JSONS ) {
                JSONS jsons = ( JSONS ) anno;
                List< JSON > jsonList = Arrays.asList( jsons.value() );
                customerJsonFilters = new CustomerJsonSerializer[jsonList.size()];
                for ( int i = 0; i < jsonList.size(); i++ ) {
                    // 判读是否地图
                    if ( jsonList.get( i ).map() ) {
                        // FIXME 这个参数 如果后期改造有配置缓存维护的话.可放入维护.方便动态
                        String type = webRequest.getParameter( "viewType" );
                        // 只处理匹配的那个json过滤
                        if ( StringUtils.hasLength( type ) && type.endsWith( jsonList.get( i ).mapType() ) ) {
                            customerJsonFilters = new CustomerJsonSerializer[1];
                            CustomerJsonSerializer filter = new CustomerJsonSerializer();
                            filter.filter( jsonList.get( i ) );
                            customerJsonFilters[0] = filter;
                        }
                    } else {
                        CustomerJsonSerializer filter = new CustomerJsonSerializer();
                        filter.filter( jsonList.get( i ) );
                        customerJsonFilters[i] = filter;
                    }
                }
            }
        }

        // 处理 json
        response.setContentType( MediaType.TEXT_PLAIN_VALUE );
        response.getWriter().write(
                JSONObject.toJSONString( returnValue, customerJsonFilters, SerializerFeature.WriteMapNullValue,
                        SerializerFeature.DisableCircularReferenceDetect ) );
    }
}
