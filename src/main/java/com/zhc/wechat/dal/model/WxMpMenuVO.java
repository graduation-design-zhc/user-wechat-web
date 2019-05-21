package com.zhc.wechat.dal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 微信菜单
 * @author zhanghuachang
 * @date 2019-05-01
 */
@Data
public class WxMpMenuVO {

    private String type;
    private String name;
    private String url;
    private String menuLevel;
    @JsonProperty(value = "sub_button")
    List<WxMpMenuVO> subButton;

}
