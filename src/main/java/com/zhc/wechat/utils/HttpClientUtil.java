package com.zhc.wechat.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;


/**
 * @author zhanghuachang
 * @date 2019-04-07
 */
@Slf4j
public class HttpClientUtil {

    private static final int SUCCESS_CODE = 200;
    private static final int TIMEOUT_TIME = 2000;
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_TIME).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response != null && response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("请求发送成功，url={}", uri);
                return resultString;
            }else {
                log.error("请求返回错误, code={}", response.getStatusLine().getStatusCode());
                return "";
            }
        } catch (Exception e) {
            log.error("地址错误，调用第三方服务超时");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                log.error("http连接关闭异常");
            }
        }
        log.error("调用第三放服务失败");
        return "";
    }

    public static String doPost(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_TIME).build();
            httpPost.setConfig(requestConfig);
            if (!StringUtils.isEmpty(json)) {
                httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
                StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
                entity.setContentType(CONTENT_TYPE_TEXT_JSON);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == SUCCESS_CODE){
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("请求发送成功，url={}", url);
                return resultString;
            }else{
                log.error("请求错误，code={}", response.getStatusLine().getStatusCode());
                return "";
            }
        } catch (Exception e) {
            log.error("地址错误，调用第三方服务超时");
        } finally {
            try {
                if (response != null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error("http连接关闭异常");
            }
        }
        log.error("调用第三放服务失败");
        return "";
    }

}
