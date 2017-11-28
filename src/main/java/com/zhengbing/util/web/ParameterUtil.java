package com.zhengbing.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * http 请求参数处理工具类
 * 
 * @author zhengbing
 *
 * @date 2017年2月16日
 */
public class ParameterUtil {

	/**
	 * 获取int类型参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Integer getIntParameter( HttpServletRequest request, String paramName ) {
		return getIntParameter( request, paramName, 0 );
	}

	/**
	 * 获取int类型参数(自定义设置默认值)
	 * 
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static Integer getIntParameter( HttpServletRequest request, String paramName, int defaultValue ) {
		String paramValue = request.getParameter( paramName );
		if( null != paramValue && !"".equals( paramValue ) ) {
			if( Integer.parseInt( paramValue ) > 0 ) {
				return Integer.parseInt( paramValue );
			}
		}

		return defaultValue;
	}

	/**
	 * 获取float类型参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Float getFloatParameter( HttpServletRequest request, String paramName ) {
		return getFloatParameter( request, paramName, 0.00f );
	}

	/**
	 * 获取float类型参数(自定义设置默认值)
	 * 
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static Float getFloatParameter( HttpServletRequest request, String paramName, float defaultValue ) {
		float paramValue = Float.parseFloat( request.getParameter( paramName ) );
		if( paramValue > 0.00f ) {
			return paramValue;
		}
		return defaultValue;
	}

	/**
	 * 获取double类型参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static double getDoubleParameter( HttpServletRequest request, String paramName ) {
		return getDoubleParameter( request, paramName, 0.01d );
	}

	/**
	 * 获取double类型参数(自定义设置默认值)
	 * 
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static double getDoubleParameter( HttpServletRequest request, String paramName, double defaultValue ) {
		if (null == request.getParameter(paramName) ){
			return defaultValue;
		}else {
			double paramValue = Double.parseDouble(request.getParameter(paramName));
			if( paramValue > 0.01d ) {
				return paramValue;
			}
			return defaultValue;
		}
	}

	/**
	 * 获取String类型参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String getParameter( HttpServletRequest request, String paramName ) {
		return getParameter( request, paramName, "" );
	}

	/**
	 * 获取String类型参数(自定义设置默认值)
	 * 
	 * @param request
	 * @param paramName
	 * @param defaultValue
	 * @return
	 */
	public static String getParameter( HttpServletRequest request, String paramName, String defaultValue ) {
		String paramValue = request.getParameter( paramName );
		if( null != paramValue && !"".equals( paramValue ) ) {
			return paramValue;
		}
		return defaultValue;
	}

}
