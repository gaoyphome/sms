package com.bmac.ffan.core.httpclient;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/1/22 14:14
 */

public class LsHttpClient {
    private static Logger log = LoggerFactory.getLogger(LsHttpClient.class);
    /**
     * HTTP POST
     */
    public static String sendHttpForPost(String url, String json) throws Exception {
        log.info("send data to FF: url=[" + url + "],jsonData=[" + json + "]");
        // String encoderJson = URLEncoder.encode(json, "UTF-8");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);// 连接超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);// 读超时

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        StringEntity se = new StringEntity(json);
        se.setContentType("text/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);

        // 读取请求内容
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String respData = EntityUtils.toString(httpResponse.getEntity());
        log.info("received from FF_puc:{}",respData);
        return respData;
    }

    public static void main(String[] args) throws Exception {
        String url = "http://192.168.1.105:19001/dataComeFromSms?pltSsn=20180131100000224782676303941632";
        String result = LsHttpClient.sendHttpForPost(url, "");
        System.out.println(result);
        Gson gson = new Gson();
        Map map =  gson.fromJson("{\n" +
                "  \"status\": 500,\n" +
                "  \"message\": \"该渠道该笔记录不存在(pls_ssn:1111111)\"\n" +
                "}",  HashMap.class);
        System.out.println(map);
    }
}