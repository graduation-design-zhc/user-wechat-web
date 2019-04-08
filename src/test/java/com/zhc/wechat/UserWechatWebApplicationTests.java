package com.zhc.wechat;

import com.alibaba.fastjson.JSON;
import com.zhc.wechat.utils.WeChatUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserWechatWebApplication.class)
public class UserWechatWebApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAccessToken() {
        String accessToken = WeChatUtil.getAccessToken();
        Map map = JSON.parseObject(accessToken, Map.class);
        System.out.println(map.get("access_token"));
    }

    @Test
    public void getFollowUser() {
        String result = WeChatUtil.getWeChatUserFollowUser();
        System.out.println(result);

    }

    @Test
    public void sendTemplateMsg() {
        String str = " {\n" +
                "           \"touser\":\"owCls1AVyvUTHqHB7eCBH2-F1y1w\",\n" +
                "           \"template_id\":\"10d2Q4DV2Jfw9fiqK-0KJP7Bc9RDLFWELgMNlJMJGZE\",\n" +
                "           \"url\":\"http://www.sina.com\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"恭喜你购买成功！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"红豆粥\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"23b4jhg324k\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\":{\n" +
                "                       \"value\":\"1.5元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\":{\n" +
                "                       \"value\":\"2019年4月8日20:20\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword5\":{\n" +
                "                       \"value\":\"1.5\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"欢迎再次购买！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = WeChatUtil.sendTemplateMsg(str);
        System.out.println(result);
    }

}
