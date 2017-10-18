package com.zhengbing.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by zhengbing on 2017/10/17.
 */
public class AuthUtil {

    public static final String APPID="wx9307e00061c0cca0";
    public static final String APPSECRET="dd4d1bb8449d651defef916d618a0893";

    public static JSONObject doGetJson(String url) throws ClientProtocolException,IOException{
        JSONObject jsonpObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if( null != entity ){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonpObject = JSONObject.fromObject(result);
        }

        httpGet.releaseConnection();
        return jsonpObject;
    }
}
