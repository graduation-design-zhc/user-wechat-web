package com.zhc.wechat;

import com.alibaba.fastjson.JSON;
import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.utils.WeChatUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserWechatWebApplication.class)
public class UserWechatWebApplicationTests {

    @Resource
    private UserWechatClient userWechatClient;

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
                "           \"template_id\":\"9MRaJnZqrE3p7bMkBuq-olLzYQrAU-NLhTjyyOI1A8Y\",\n" +
                "           \"url\":\"http://www.sina.com\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"余额明细\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"556.00元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"234\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"祝您生活愉快！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = WeChatUtil.sendTemplateMsg(str);
        System.out.println(result);
    }

    @Test
    public void getUserList() {
        Response<List<UserDTO>> response = userWechatClient.getUserList();
        assert response.getData() != null;
    }

    @Test
    public void getUserLogin() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName("admin");
        userRequest.setPassword("admin");
        Response<UserDTO> response = userWechatClient.getUserLogin(userRequest);
        assert response.getData() != null;
    }

}
