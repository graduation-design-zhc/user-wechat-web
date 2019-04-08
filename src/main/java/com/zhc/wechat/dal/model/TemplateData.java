package com.zhc.wechat.dal.model;

import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

/**
 * @author zhanghuachang
 * @date 2019-04-07
 */
@Data
public class TemplateData {

    @Description("模板显示值")
    private String value;
    @Description("模板显示颜色")
    private String color;

}
