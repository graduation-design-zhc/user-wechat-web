package com.zhc.wechat.dal.model;

import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

import java.util.Map;

/**
 * @author zhanghuachang
 * @date 2019-04-07
 */
@Data
public class WeChatPushMessage {

    @Description("模板id")
    private String templateId;
    @Description("消息接收方")
    private String toUser;
    @Description("模板消息详情链接")
    private String url;
    @Description("消息顶部的颜色")
    private String topColor;
    @Description("参数列表")
    private Map<String,TemplateData> data;

}
