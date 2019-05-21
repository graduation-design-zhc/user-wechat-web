package com.zhc.wechat;

import com.alibaba.fastjson.JSON;
import com.user.wechat.api.dto.MemberCardDTO;
import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.dal.model.WxMpMenuVO;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.utils.WeChatUtil;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserWechatWebApplication.class)
public class UserWechatWebApplicationTests {

    @Resource
    private UserWechatClient userWechatClient;

    @Resource
    private WxMpService wxMpService;

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
                "           \"template_id\":\"0lmJ1w9G2ttJzpgJDOeMvCgDYpR66yJHnGSIRmvaSEU\",\n" +
                "           \"url\":\"http://www.sina.com\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"1.00元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"2.00元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\":{\n" +
                "                       \"value\":\"33\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"2019-05-01\",\n" +
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

    @Test
    public void getCards() {
        List<String> memberIds = new ArrayList<>();
        memberIds.add("9e5c9e6b347e4f4c81a37c714cdd807e");
        Response<List<MemberCardDTO>> response = userWechatClient.getCardsByMemberIds(memberIds);
        System.out.println(response);

    }

    @Test
    public void wxMenu() {
        //            WxMpMenu wxMpMenu = wxMpService.getMenuService().menuGet();
//            System.out.println(wxMpMenu.toString());
        WxMenu wxMenu = new WxMenu();

        WxMpMenu wxMpMenu1 = new WxMpMenu();
        //创建一级菜单
        WxMenuButton button1=new WxMenuButton();
        button1.setType("view"); //点击事件按钮
        button1.setName("会员中心");
//        button1.setKey("key1"); //根据标志获取点击菜单
        button1.setUrl("http://sougou.com");
        List<WxMenuButton> buttons = new ArrayList<>();
        buttons.add(button1);
        wxMenu.setButtons(buttons);
        try {
            String s = wxMpService.getMenuService().menuCreate(wxMenu);
            System.out.println(s);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }

}
