package com.zhc.wechat.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghuachang
 * @date 2019-04-07
 */
@Slf4j
public class WeChatUtil {

    private final static String APP_ID = "wx4717e81b20c15d7d";
    private final static String APP_SECRET = "87c4a22527e102e5edd6afc67638bfee";

    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private final static String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    private final static String USER_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

    /**
     * 获取accessToken
     * @return
     */
    public static String getAccessToken() {
        Map<String, String> map = new HashMap<>(8);
        map.put("grant_type", "client_credential");
        map.put("appid", APP_ID);
        map.put("secret", APP_SECRET);
        return HttpClientUtil.doGet(ACCESS_TOKEN_URL, map);
    }


    public static String sendTemplateMsg(String json) {
        String accessToken = WeChatUtil.getAccessToken();
        Map map = JSON.parseObject(accessToken, Map.class);
        return HttpClientUtil.doPost(TEMPLATE_URL + map.get("access_token"), json);
    }

    public static String getWeChatUserFollowUser() {
        String accessToken = WeChatUtil.getAccessToken();
        Map map = JSON.parseObject(accessToken, Map.class);
        return HttpClientUtil.doGet(USER_GET_URL + map.get("access_token"), null);
    }

}
